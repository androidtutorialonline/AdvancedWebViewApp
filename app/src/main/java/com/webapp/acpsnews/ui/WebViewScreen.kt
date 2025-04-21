package com.webapp.acpsnews.ui

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.net.Uri
import android.webkit.*
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

@SuppressLint("SetJavaScriptEnabled", "UnusedMaterialScaffoldPaddingParameter")
@Composable
fun WebViewScreen(viewModel: WebViewViewModel) {
    val context = LocalContext.current
    var webView: WebView? = null
    var filePathCallback by remember { mutableStateOf<ValueCallback<Array<Uri>>?>(null) }

    val filePickerLauncher = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) {
        filePathCallback?.onReceiveValue(arrayOf(it ?: Uri.EMPTY))
        filePathCallback = null
    }

    val swipeRefreshState = rememberSwipeRefreshState(isRefreshing = false)

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Advanced WebView") },
                actions = {
                    IconButton(onClick = {
                        webView?.goBack()
                    }, enabled = viewModel.canGoBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                    IconButton(onClick = {
                        webView?.goForward()
                    }, enabled = viewModel.canGoForward) {
                        Icon(Icons.Default.ArrowForward, contentDescription = "Forward")
                    }
                    IconButton(onClick = {
                        webView?.reload()
                    }) {
                        Icon(Icons.Default.Refresh, contentDescription = "Refresh")
                    }
                }
            )
        }
    ) {
        SwipeRefresh(
            state = swipeRefreshState,
            onRefresh = {
                viewModel.setRefreshing(true)
                webView?.reload()
            },
            modifier = Modifier.fillMaxSize()
        ) {
            AndroidView(
                factory = {
                    WebView(it).apply {
                        webView = this
                        settings.javaScriptEnabled = true
                        settings.allowFileAccess = true
                        settings.domStorageEnabled = true
                        settings.javaScriptCanOpenWindowsAutomatically = true
                        settings.setSupportMultipleWindows(true)

                        webViewClient = object : WebViewClient() {
                            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                                viewModel.setRefreshing(true)
                            }

                            override fun onPageFinished(view: WebView?, url: String?) {
                                viewModel.updateNavigationState(
                                    canGoBack = canGoBack(),
                                    canGoForward = canGoForward()
                                )
                                viewModel.setRefreshing(false)

                                // JavaScript injection
                                evaluateJavascript("document.body.style.backgroundColor = '#FAFAFA';", null)
                            }

                            override fun onReceivedError(
                                view: WebView?,
                                request: WebResourceRequest?,
                                error: WebResourceError?
                            ) {
                                viewModel.setRefreshing(false)
                                Toast.makeText(context, "Error: ${error?.description}", Toast.LENGTH_SHORT).show()
                            }
                        }

                        webChromeClient = object : WebChromeClient() {
                            override fun onShowFileChooser(
                                webView: WebView?,
                                filePath: ValueCallback<Array<Uri>>?,
                                fileChooserParams: FileChooserParams?
                            ): Boolean {
                                filePathCallback = filePath
                                filePickerLauncher.launch("*/*")
                                return true
                            }
                        }

                        loadUrl(viewModel.url)
                    }
                },
                update = { webView = it }
            )
        }
    }
}
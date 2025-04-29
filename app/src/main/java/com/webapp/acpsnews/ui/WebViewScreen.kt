package com.webapp.acpsnews.ui

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.net.Uri
import android.util.Log
import android.webkit.ValueCallback
import android.webkit.WebChromeClient
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.webapp.acpsnews.MenuItem
import com.webapp.acpsnews.R

@SuppressLint("SetJavaScriptEnabled", "UnusedMaterialScaffoldPaddingParameter")
@Composable
fun WebViewScreen(viewModel: WebViewViewModel, pageName: String? = "") {
    val url by viewModel.url.collectAsState()
    var webView: WebView? = null
    var isWeb by remember { mutableStateOf("webView") }
    var filePathCallback by remember { mutableStateOf<ValueCallback<Array<Uri>>?>(null) }

    val filePickerLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) {
            filePathCallback?.onReceiveValue(arrayOf(it ?: Uri.EMPTY))
            filePathCallback = null
        }

    val swipeRefreshState = rememberSwipeRefreshState(isRefreshing = false)

    Scaffold(

        bottomBar = {
            BottomNavigation(elevation = 8.dp) {
                var items =
                    mutableListOf(
                        MenuItem("News", "https://acpsnews.in", R.drawable.newspaper),
                        MenuItem(
                            "TV",
                            "https://www.youtube.com/@sahasraacpanews",
                            R.drawable.television,
                        ),
                        MenuItem("Radio", " ", R.drawable.radio),
                        MenuItem("e-Paper", "https://acpsnews.in/epaper", R.drawable.newspaper),
                    )

                if (pageName == "Privacy Policy") {
                    items.add(MenuItem("Privacy Policy", " ", R.drawable.location))
                } else {
                    items.add(MenuItem("Contact", " ", R.drawable.location))
                }

                items.forEach { (label, link, icon) ->

                    BottomNavigationItem(
                        icon = {
                            Icon(
                                painter = painterResource(id = icon),
                                contentDescription = null,
                                modifier = Modifier.padding(16.dp, 8.dp, 16.dp, 8.dp),
                            )
                        },
                        label = { Text(label, style = MaterialTheme.typography.body1) },
                        selected = url == link,
                        onClick = {
                            isWeb = label
                            viewModel.updateUrl(link)
                            webView?.loadUrl(link)
                        },
                    )
                }
            }
        },
    ) { paddingValues ->
        // Content of your screen goes here
        // Use Modifier.padding(paddingValues) to avoid content overlapping with top and bottom bars.
        SwipeRefresh(
            state = swipeRefreshState,
            onRefresh = {
                viewModel.setRefreshing(true)
                webView?.reload()
            },
            modifier = Modifier.fillMaxSize(),
        ) {
            if (isWeb == "Contact") {
                AboutUsContent(viewModel)
            } else if (isWeb == "Radio") {
                RadioScreenContent()
            } else {
                AndroidView(
                    modifier = Modifier
                        .fillMaxSize()
                        .statusBarsPadding()
                        .navigationBarsPadding(),
                    factory = {
                        WebView(it).apply {
                            webView = this
                            settings.javaScriptEnabled = true
                            settings.allowFileAccess = true
                            settings.domStorageEnabled = true
                            settings.javaScriptCanOpenWindowsAutomatically = true
                            settings.setSupportMultipleWindows(true)

                            webViewClient = object : WebViewClient() {
                                override fun onPageStarted(
                                    view: WebView?,
                                    url: String?,
                                    favicon: Bitmap?,
                                ) {
                                    viewModel.setRefreshing(true)
                                }

                                override fun onPageFinished(view: WebView?, url: String?) {
                                    viewModel.updateNavigationState(
                                        canGoBack = canGoBack(),
                                        canGoForward = canGoForward(),
                                    )
                                    viewModel.setRefreshing(false)

                                    // JavaScript injection
                                    evaluateJavascript(
                                        "document.body.style.backgroundColor = '#FAFAFA';",
                                        null,
                                    )
                                }

                                override fun onReceivedError(
                                    view: WebView?,
                                    request: WebResourceRequest?,
                                    error: WebResourceError?,
                                ) {
                                    viewModel.setRefreshing(false)

                                    Log.e("WebViewScreen", "Error: ${error?.description}")
                                }
                            }

                            webChromeClient = object : WebChromeClient() {
                                override fun onShowFileChooser(
                                    webView: WebView?,
                                    filePath: ValueCallback<Array<Uri>>?,
                                    fileChooserParams: FileChooserParams?,
                                ): Boolean {
                                    filePathCallback = filePath
                                    filePickerLauncher.launch("*/*")
                                    return true
                                }
                            }
                            Log.d("WebViewScreen", "Loading URL: $url")
                            loadUrl(viewModel.url.value)
                        }
                    },
                    update = { webView = it },
                )
            }
        }
    }
}

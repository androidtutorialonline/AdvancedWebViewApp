package com.webapp.acpsnews.webview

import android.annotation.SuppressLint
import android.net.Uri
import android.webkit.*
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.pullrefresh.PullRefreshIndicator
import androidx.compose.material3.pullrefresh.pullRefresh
import androidx.compose.material3.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import kotlinx.coroutines.delay

@SuppressLint("SetJavaScriptEnabled")
@Composable
fun WebViewScreen(url: String) {
    val context = LocalContext.current
    var isRefreshing by remember { mutableStateOf(false) }
    val pullRefreshState = rememberPullRefreshState(isRefreshing, { isRefreshing = true })

    var isLoading by remember { mutableStateOf(true) }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    val fileChooserLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        uploadMessage?.onReceiveValue(uri)
        uploadMessage = null
    }

    Box(Modifier.fillMaxSize().pullRefresh(pullRefreshState)) {
        AndroidView(factory = {
            WebView(context).apply {
                layoutParams = WebView.LayoutParams(
                    WebView.LayoutParams.MATCH_PARENT,
                    WebView.LayoutParams.MATCH_PARENT
                )
                webChromeClient = object : WebChromeClient() {
                    override fun onShowFileChooser(
                        view: WebView?,
                        filePathCallback: ValueCallback<Array<Uri>>?,
                        fileChooserParams: FileChooserParams?
                    ): Boolean {
                        uploadMessageArray = filePathCallback
                        fileChooserLauncher.launch("*/*")
                        return true
                    }
                }
                webViewClient = object : WebViewClient() {
                    override fun onPageFinished(view: WebView?, url: String?) {
                        isLoading = false
                        isRefreshing = false
                    }

                    override fun onReceivedError(
                        view: WebView?,
                        request: WebResourceRequest?,
                        error: WebResourceError?
                    ) {
                        errorMessage = "Failed to load page: ${error?.description}"
                        isLoading = false
                        isRefreshing = false
                    }
                }
                settings.javaScriptEnabled = true
                settings.domStorageEnabled = true
                settings.allowFileAccess = true
                settings.javaScriptCanOpenWindowsAutomatically = true
                loadUrl(url)
            }
        })

        if (isLoading) {
            CircularProgressIndicator(Modifier.align(Alignment.Center))
        }

        errorMessage?.let {
            Text(text = it, color = MaterialTheme.colorScheme.error, modifier = Modifier.align(Alignment.Center))
        }

        PullRefreshIndicator(isRefreshing, pullRefreshState, Modifier.align(Alignment.TopCenter))
    }

    // Refresh simulation
    LaunchedEffect(isRefreshing) {
        if (isRefreshing) delay(1000)
        isRefreshing = false
    }
}

private var uploadMessage: ValueCallback<Uri?>? = null
private var uploadMessageArray: ValueCallback<Array<Uri>>? = null

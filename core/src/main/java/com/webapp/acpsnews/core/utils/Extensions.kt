package com.webapp.acpsnews.core.utils

import android.webkit.WebView
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import kotlinx.coroutines.delay

fun WebView.clearAll() {
    this.clearHistory()
    this.clearCache(true)
    this.loadUrl("about:blank")
    this.clearFormData()
}

@Composable
fun AutoRetryEffect(retryInterval: Long = 3000L, retryAction: () -> Unit) {
    LaunchedEffect(Unit) {
        while (true) {
            delay(retryInterval)
            retryAction()
        }
    }
}

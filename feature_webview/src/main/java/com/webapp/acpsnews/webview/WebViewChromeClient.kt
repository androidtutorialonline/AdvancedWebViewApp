package com.webapp.acpsnews.webview

import android.webkit.WebChromeClient
import android.webkit.WebView

class WebViewChromeClient : WebChromeClient() {
    override fun onProgressChanged(view: WebView?, newProgress: Int) {
        super.onProgressChanged(view, newProgress)
        // Update loading progress if necessary
    }

    override fun onReceivedTitle(view: WebView?, title: String?) {
        super.onReceivedTitle(view, title)
        // Handle title updates if necessary
    }
}

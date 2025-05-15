package com.webapp.acpsnews.webview

import androidx.compose.runtime.mutableStateOf


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class WebViewViewModel : ViewModel() {
    // You can manage any WebView-related state here
    private val _urlState = mutableStateOf("https://news.example.com")
    val urlState = _urlState

    fun loadUrl(url: String) {
        viewModelScope.launch {
            _urlState.value = url
        }
    }
}

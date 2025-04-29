package com.webapp.acpsnews.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class WebViewViewModel : ViewModel() {
    private val _currentUrl = MutableStateFlow("https://acpsnews.in")
    val url: StateFlow<String> = _currentUrl

    fun updateUrl(url: String) {
        _currentUrl.value = url
    }
    var canGoBack by mutableStateOf(false)
    var canGoForward by mutableStateOf(false)
    // var isRefreshing by mutableStateOf(false)

    fun updateNavigationState(canGoBack: Boolean, canGoForward: Boolean) {
        this.canGoBack = canGoBack
        this.canGoForward = canGoForward
    }

    fun setRefreshing(refreshing: Boolean) {
        // isRefreshing = refreshing
    }
}

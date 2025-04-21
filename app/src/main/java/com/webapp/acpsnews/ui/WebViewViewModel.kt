package com.webapp.acpsnews.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class WebViewViewModel : ViewModel() {
    var url by mutableStateOf("https://acpsnews.in")
    var canGoBack by mutableStateOf(false)
    var canGoForward by mutableStateOf(false)
    //var isRefreshing by mutableStateOf(false)

    fun updateNavigationState(canGoBack: Boolean, canGoForward: Boolean) {
        this.canGoBack = canGoBack
        this.canGoForward = canGoForward
    }

    fun setRefreshing(refreshing: Boolean) {
        //isRefreshing = refreshing
    }
}
package com.webapp.acpsnews.webview.navigation

sealed class NavigationItem(val route: String, val title: String) {
    object News : NavigationItem("news", "News")
    object TV : NavigationItem("tv", "TV")
    object Radio : NavigationItem("radio", "Radio")
    object Epaper : NavigationItem("epaper", "e-Paper")
    object Contact : NavigationItem("contact", "Contact")
}

package com.webapp.acpsnews.navigation


import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.feature_webview.WebViewScreen

@Composable
fun AppNavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "news") {
        composable("news") { WebViewScreen("https://news.example.com") }
        composable("tv") { WebViewScreen("https://tv.example.com") }
        composable("radio") { WebViewScreen("https://radio.example.com") }
        composable("epaper") { WebViewScreen("https://epaper.example.com") }
        composable("contact") { WebViewScreen("https://contact.example.com") }
    }
}

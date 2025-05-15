package com.webapp.acpsnews.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun AppNavGraph(navController: NavController) {
    NavHost(navController = navController, startDestination = "news") {
        composable("news") {
            WebViewScreen(
                viewModel = WebViewViewModel(), // Assuming WebViewViewModel is set up here
                url = "https://news.example.com"
            )
        }
        composable("tv") {
            WebViewScreen(
                viewModel = WebViewViewModel(),
                url = "https://tv.example.com"
            )
        }
        composable("radio") {
            WebViewScreen(
                viewModel = WebViewViewModel(),
                url = "https://radio.example.com"
            )
        }
        composable("epaper") {
            WebViewScreen(
                viewModel = WebViewViewModel(),
                url = "https://epaper.example.com"
            )
        }
        composable("contact") {
            WebViewScreen(
                viewModel = WebViewViewModel(),
                url = "https://contact.example.com"
            )
        }
    }
}

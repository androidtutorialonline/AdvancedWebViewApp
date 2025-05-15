package com.webapp.acpsnews.navigation

import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.navigation.compose.*

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val items = listOf(
        NavigationItem.News,
        NavigationItem.TV,
        NavigationItem.Radio,
        NavigationItem.Epaper,
        NavigationItem.Contact
    )

    Scaffold(
        bottomBar = {
            NavigationBar {
                val currentRoute = currentBackStackEntryAsState().value?.destination?.route
                items.forEach { item ->
                    NavigationBarItem(
                        selected = currentRoute == item.route,
                        onClick = { navController.navigate(item.route) },
                        label = { Text(item.title) },
                        icon = {}
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = NavigationItem.News.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(NavigationItem.News.route) {
                WebViewScreen(url = "https://news.example.com")
            }
            composable(NavigationItem.TV.route) {
                WebViewScreen(url = "https://tv.example.com")
            }
            composable(NavigationItem.Radio.route) {
                WebViewScreen(url = "https://radio.example.com")
            }
            composable(NavigationItem.Epaper.route) {
                WebViewScreen(url = "https://epaper.example.com")
            }
            composable(NavigationItem.Contact.route) {
                WebViewScreen(url = "https://contact.example.com")
            }
        }
    }
}

package com.webapp.acpsnews.navigation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController

class NavHostActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AdvancedWebViewAppTheme {
                // App scaffold with a Bottom Navigation bar
                Scaffold(
                    bottomBar = { BottomNavBar(navController = rememberNavController()) }
                ) {
                    AppNavGraph(navController = rememberNavController())
                }
            }
        }
    }
}

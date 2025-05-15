package com.webapp.acpsnews.navigation

import androidx.navigation.NavController

fun navigateToScreen(navController: NavController, screenName: String) {
    navController.navigate(screenName) {
        popUpTo(screenName) { inclusive = true }
    }
}

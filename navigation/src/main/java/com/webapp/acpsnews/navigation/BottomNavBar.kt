package com.webapp.acpsnews.navigation

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.BottomNavigation
import androidx.compose.material3.BottomNavigationItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Radio
import androidx.compose.material.icons.filled.VideoLibrary
import androidx.compose.material.icons.filled.ContactMail
import androidx.compose.material.icons.filled.Newspaper
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController

@Composable
fun BottomNavBar(navController: NavController) {
    BottomNavigation(
        modifier = Modifier.fillMaxWidth()
    ) {
        BottomNavigationItem(
            icon = { androidx.compose.material.Icon(Icons.Filled.Newspaper, contentDescription = "News") },
            label = { androidx.compose.material.Text("News") },
            selected = false,
            onClick = { navController.navigate("news") }
        )
        BottomNavigationItem(
            icon = { androidx.compose.material.Icon(Icons.Filled.VideoLibrary, contentDescription = "TV") },
            label = { androidx.compose.material.Text("TV") },
            selected = false,
            onClick = { navController.navigate("tv") }
        )
        BottomNavigationItem(
            icon = { androidx.compose.material.Icon(Icons.Filled.Radio, contentDescription = "Radio") },
            label = { androidx.compose.material.Text("Radio") },
            selected = false,
            onClick = { navController.navigate("radio") }
        )
        BottomNavigationItem(
            icon = { androidx.compose.material.Icon(Icons.Filled.Newspaper, contentDescription = "e-Paper") },
            label = { androidx.compose.material.Text("e-Paper") },
            selected = false,
            onClick = { navController.navigate("epaper") }
        )
        BottomNavigationItem(
            icon = { androidx.compose.material.Icon(Icons.Filled.ContactMail, contentDescription = "Contact") },
            label = { androidx.compose.material.Text("Contact") },
            selected = false,
            onClick = { navController.navigate("contact") }
        )
    }
}

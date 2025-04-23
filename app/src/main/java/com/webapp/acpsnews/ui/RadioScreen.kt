package com.webapp.acpsnews.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.*
import androidx.compose.runtime.Composable

class RadioScreen : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme { // Wrap your content in a MaterialTheme
                RadioScreenContent()
            }
        }
    }
}

@Composable
fun RadioScreenContent() {
    Text("This is the Radio screen! Coming Soon.")  // *Something* must render here!Us screen!")  // *Something* must render here!
}


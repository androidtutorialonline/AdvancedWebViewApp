package com.webapp.acpsnews

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import com.webapp.acpsnews.ui.WebViewScreen
import com.webapp.acpsnews.ui.WebViewViewModel

class MainActivity : ComponentActivity() {
    private val viewModel: WebViewViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {

        enableEdgeToEdge() // Add this
        super.onCreate(savedInstanceState)
        setContent {
            val data = intent.extras
            WebViewScreen(viewModel,  data?.getString("pageName") ?:" ")
        }
    }
}
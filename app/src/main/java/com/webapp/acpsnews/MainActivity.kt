package com.webapp.acpsnews

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.dp
import com.webapp.acpsnews.MainActivity.Companion.items
import com.webapp.acpsnews.ui.AboutUsScreen
import com.webapp.acpsnews.ui.WebViewScreen
import com.webapp.acpsnews.ui.WebViewViewModel


class MainActivity : ComponentActivity() {
    private val viewModel: WebViewViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            //WebViewScreen(viewModel)

            AppContent1(viewModel)
        }
    }

    companion object {
        val items = listOf(
            "News" to "https://acpsnews.in",
            "TV" to "https://www.youtube.com/@sahasraacpanews",
            "Radio" to "https://acpsnews.in",
            "e-Paper" to "https://acpsnews.in/epaper",
            "Contact" to "https://acpsnews.in"
        )
    }
}


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MyBottomNavigation(
    onScreenSelected: (String) -> Unit,
    viewModel: WebViewViewModel
) {

    BottomNavigation(elevation = 8.dp) {
        items.forEach { (label, link) ->
            BottomNavigationItem(
                icon = {},
                label = { Text(label) },
                selected = "url" == link,
                onClick = {
                    onScreenSelected(label)  // Now used for ALL navigation
                    if (label == "Contact") { // Example: Assuming you have an "About Us" item
                        //AboutUsScreen()
                        //currentScreen = "AboutUsScreen"
                    } else {
                        viewModel.updateUrl(link)
                        //currentScreen = "Home"
                        //webView?.loadUrl(link)
                    }
                }
            )
        }
    }
}

@Composable
fun AppContent1(viewModel: WebViewViewModel) {
    var currentScreen by remember { mutableStateOf("Home") }

    when (currentScreen) {
        "Home" -> WebViewScreen(viewModel)
        "AboutUsScreen" -> AboutUsScreen()  // Now call the composable
        // ... other screens ...
    }

    MyBottomNavigation(
        onScreenSelected = { screen -> currentScreen = screen },
        viewModel
    )
}


@Composable
fun AboutUsScreen() {
    MaterialTheme { // Or MaterialTheme3
        Text("This is the About Us screen!")  // Replace with your actual content
        // ... Add your other UI elements for the "About Us" screen ...
    }
}


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun AppContent(viewModel: WebViewViewModel) {
    var currentScreen by remember { mutableStateOf("Home") } // State for current screen

    when (currentScreen) {

        "Home" -> WebViewScreen(viewModel)
        "AboutUsScreen" -> AboutUsScreen()
        // ... other screens ...
    }



    Scaffold(

        /*topBar = {
            TopAppBar(

                title = { Text("Sahasra acps news") },
                actions = {
                    IconButton(onClick = {
                        webView?.goBack()
                    }, enabled = viewModel.canGoBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                    IconButton(onClick = {
                        webView?.goForward()
                    }, enabled = viewModel.canGoForward) {
                        Icon(Icons.Default.ArrowForward, contentDescription = "Forward")
                    }
                    IconButton(onClick = {
                        webView?.reload()
                    }) {
                        Icon(Icons.Default.Refresh, contentDescription = "Refresh")
                    }
                }
            )
        },*/
        bottomBar = {
            BottomNavigation(elevation = 8.dp) {

                //https://acpsnews.in/privacy-policy/

                items.forEach { (label, link) ->
                    BottomNavigationItem(
                        icon = {},
                        label = { Text(label) },
                        selected = "url" == link,
                        onClick = {
                            if (label == "Contact") { // Example: Assuming you have an "About Us" item
                                //AboutUsScreen()
                                currentScreen = "AboutUsScreen"
                            } else {
                                viewModel.updateUrl(link)
                                currentScreen = "Home"
                                //webView?.loadUrl(link)
                            }
                        }
                    )
                }
            }
        }
    ) { paddingValues ->
        // Content of your screen goes here
        // Use Modifier.padding(paddingValues) to avoid content overlapping with top and bottom bars.
        /*SwipeRefresh(
            state = swipeRefreshState,
            onRefresh = {
                viewModel.setRefreshing(true)
                //webView?.reload()
            },
            modifier = Modifier.fillMaxSize()
        ) {



        }*/
    }


}

package com.webapp.acpsnews.ui

import android.R.attr.bottom
import android.R.attr.end
import android.R.attr.top
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester.Companion.createRefs
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

class RadioScreen : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        enableEdgeToEdge() // Add this
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

    /* Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        contentAlignment = Alignment.Center
    )*/
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            modifier = Modifier.padding(24.dp),
            text = "This is the Radio screen! Coming Soon.",
            textAlign = TextAlign.Center)
    }


    /*ConstraintLayout(modifier = Modifier.fillMaxSize()) {
        val (text) = createRefs()
        Text(
            modifier = Modifier.constrainAs(text) {
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            },
            text = "This is the Radio screen! Coming Soon.",
            textAlign = TextAlign.Center,
        )
    }*/

}


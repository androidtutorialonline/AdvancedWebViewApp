package com.webapp.acpsnews.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.font.FontWeight
import androidx.core.content.ContextCompat.startActivity
import com.webapp.acpsnews.MainActivity
import kotlin.getValue


class AboutUsScreen : ComponentActivity() {
    private val viewModel: WebViewViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme { // Wrap your content in a MaterialTheme
                AboutUsContent(viewModel, this)
            }
        }
    }
}

@Composable
fun AboutUsContent(viewModel: WebViewViewModel, aboutUsScreen: AboutUsScreen) {
    var clickPrivacyPolicy by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = "About Us",
            style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold),
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Text(
            text = "SAHASRA ACPS NEWS CHANNEL",
            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.SemiBold),
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Text(
            text = "Head Office Address:",
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium
        )
        Text(
            text = "AK Plaza, Sharada Nagar, LB Nagar, Ranga Reddy, Hyderabad, Telangana",
            fontSize = 14.sp,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Text(
            text = "Cell No: 9581706709",
            fontSize = 14.sp
        )
        Button(onClick = {
            clickPrivacyPolicy = true

        }) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                //Icon(Icons.Filled.P, contentDescription = "Favorite Icon")
                Spacer(Modifier.width(8.dp))
                Text("Privacy Policy")
            }
        }
    }

    if(clickPrivacyPolicy) {
        clickPrivacyPolicy = false
        PrivacyPolicyContent(viewModel, aboutUsScreen)
    }

}


@Composable
fun PrivacyPolicyContent(viewModel: WebViewViewModel, aboutUsScreen: AboutUsScreen) {
    viewModel.updateUrl("https://acpsnews.in/privacy-policy/")
    //WebViewScreen(viewModel, "Privacy Policy")
    val intent = Intent(aboutUsScreen, MainActivity::class.java)
    intent.putExtra("pageName", "Privacy Policy")
    aboutUsScreen.startActivity(intent)
}

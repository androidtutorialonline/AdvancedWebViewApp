package com.webapp.acpsnews.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.webapp.acpsnews.MainActivity

class AboutUsScreen : ComponentActivity() {
    private val viewModel: WebViewViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge() // Add this
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme { // Wrap your content in a MaterialTheme
                AboutUsContent(viewModel)
            }
        }
    }
}

@Composable
fun AboutUsContent(viewModel: WebViewViewModel) {
    var clickPrivacyPolicy by remember { mutableStateOf(false) }

    val context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start,
    ) {
        Text(
            text = "About Us",
            style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold),
            modifier = Modifier.padding(bottom = 16.dp),
        )

        Text(
            text = "SAHASRA ACPS NEWS CHANNEL",
            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.SemiBold),
            modifier = Modifier.padding(bottom = 8.dp),
        )

        Text(
            text = "Head Office Address:",
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
        )
        Text(
            text = "AK Plaza, Sharada Nagar, LB Nagar, Ranga Reddy, Hyderabad, Telangana",
            fontSize = 14.sp,
            modifier = Modifier.padding(bottom = 8.dp),
        )

        Text(
            text = "Cell No: 9581706709",
            fontSize = 14.sp,
        )
        Button(onClick = {
            clickPrivacyPolicy = true
        }) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Spacer(Modifier.width(8.dp))
                Text("Privacy Policy")
            }
        }
    }

    if (clickPrivacyPolicy) {
        PrivacyPolicyContent(viewModel, context)
    }
}

@Composable
fun PrivacyPolicyContent(viewModel: WebViewViewModel, context: Context) {
    viewModel.updateUrl("https://acpsnews.in/privacy-policy/")
    val intent = Intent(context, MainActivity::class.java)
    intent.putExtra("pageName", "Privacy Policy")
    context.startActivity(intent)
}

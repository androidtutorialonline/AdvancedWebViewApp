package com.webapp.acpsnews.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.font.FontWeight

@Composable
fun AboutUsScreen() {
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
    }
}

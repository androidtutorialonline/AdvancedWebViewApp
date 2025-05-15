package com.webapp.acpsnews

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.webapp.acpsnews.navigation.MainScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            //AppTheme {
                MainScreen()
            //}
        }
    }
}

/*Firebase.auth.signInAnonymously()
.addOnCompleteListener { task ->
    if (task.isSuccessful) {
        val user = Firebase.auth.currentUser
        Log.d("Auth", "Signed in as ${user?.uid}")
    } else {
        Log.e("Auth", "Sign-in failed", task.exception)
    }
}*/


/*
🔥 Step 4: Firestore Example kotlin

val db = Firebase.firestore
val user = hashMapOf("name" to "Firoj", "email" to "firoj@example.com")

db.collection("users").add(user)
.addOnSuccessListener { Log.d("Firestore", "DocumentSnapshot added!") }
.addOnFailureListener { e -> Log.w("Firestore", "Error adding document", e) }
*/


/*val remoteConfig = Firebase.remoteConfig
remoteConfig.fetchAndActivate().addOnCompleteListener {
    if (it.isSuccessful) {
        val newsUrl = remoteConfig.getString("url_news")
        val tvUrl = remoteConfig.getString("url_tv")
        // use these URLs in your navigation or WebView loading logic
    }
}
*/




/*
* ./gradlew clean build
./gradlew assembleDevDebug
# or
./gradlew installDevDebug

*
* */
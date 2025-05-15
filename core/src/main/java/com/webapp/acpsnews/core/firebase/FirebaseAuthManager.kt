package com.webapp.acpsnews.core.firebase

import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await

class FirebaseAuthManager(private val auth: FirebaseAuth = FirebaseAuth.getInstance()) {

    suspend fun signInAnonymously(): String? {
        return try {
            val result = auth.signInAnonymously().await()
            result.user?.uid
        } catch (e: Exception) {
            null
        }
    }

    fun isUserSignedIn(): Boolean = auth.currentUser != null
}

package com.webapp.acpsnews.core.firebase

import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class FirestoreManager(
    private val firestore: FirebaseFirestore = FirebaseFirestore.getInstance()
) {
    suspend fun saveUserData(userId: String, data: Map<String, Any>) {
        firestore.collection("users")
            .document(userId)
            .set(data)
            .await()
    }

    suspend fun getUserData(userId: String): Map<String, Any>? {
        val doc = firestore.collection("users").document(userId).get().await()
        return if (doc.exists()) doc.data else null
    }
}

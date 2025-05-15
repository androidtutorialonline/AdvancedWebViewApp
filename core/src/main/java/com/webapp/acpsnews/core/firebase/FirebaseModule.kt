package com.webapp.acpsnews.core.firebase


import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object FirebaseModule {

    @Provides
    fun provideFirebaseAuth(): FirebaseAuth = FirebaseAuth.getInstance()

    @Provides
    fun provideFirestore(): FirebaseFirestore = FirebaseFirestore.getInstance()

    @Provides
    fun provideAuthManager(auth: FirebaseAuth): FirebaseAuthManager = FirebaseAuthManager(auth)

    @Provides
    fun provideFirestoreManager(firestore: FirebaseFirestore): FirestoreManager = FirestoreManager(firestore)
}


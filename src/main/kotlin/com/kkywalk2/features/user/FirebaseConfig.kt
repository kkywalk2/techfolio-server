package com.kkywalk2.features.user

import com.google.auth.oauth2.GoogleCredentials
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import com.google.firebase.auth.FirebaseAuth
import io.ktor.server.application.*

object FirebaseConfig {
    private var firebaseAuth: FirebaseAuth? = null

    fun initialize(application: Application) {
        if (FirebaseApp.getApps().isEmpty()) {
            val serviceAccount = application::class.java.classLoader.getResourceAsStream("firebase-service-account.json")
                ?: throw IllegalStateException("firebase-service-account.json not found in resources")
            
            val options = FirebaseOptions.builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .build()
            FirebaseApp.initializeApp(options)
        }
        firebaseAuth = FirebaseAuth.getInstance()
    }

    fun getAuth(): FirebaseAuth {
        return firebaseAuth ?: throw IllegalStateException("Firebase Auth not initialized")
    }
}

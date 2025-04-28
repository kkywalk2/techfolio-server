package com.kkywalk2.features.auth

import kotlinx.serialization.Serializable

@Serializable
data class OnboardRequest(
    val idToken: String,
    val email: String,
    val name: String,
)

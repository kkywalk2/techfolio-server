package com.kkywalk2.features.auth

import kotlinx.serialization.Serializable

@Serializable
data class OnboardResponse(
    val id: String,
    val email: String,
    val name: String
)

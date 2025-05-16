package com.kkywalk2.features.user

import kotlinx.serialization.Serializable

@Serializable
data class CreateUserRequest(
    val idToken: String,
    val email: String,
    val name: String,
)

package com.kkywalk2.features.user

import kotlinx.serialization.Serializable

@Serializable
data class CreateUserResponse(
    val id: String,
    val email: String,
    val name: String
)

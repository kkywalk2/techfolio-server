package com.kkywalk2.features.user

import arrow.core.Either
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.util.*
import io.ktor.server.response.*

class AuthPluginConfig {
    lateinit var userService: UserService
}

val UserKey = AttributeKey<User>("AuthenticatedUser")

val AuthPlugin = createRouteScopedPlugin(
    name = "AuthPlugin",
    createConfiguration = ::AuthPluginConfig,
) {
    val authService = pluginConfig.userService

    onCall { call ->
        val token = call.request.headers["Authorization"]?.removePrefix("Bearer ")

        if (token == null) {
            call.respond(HttpStatusCode.Unauthorized)
            return@onCall
        }

        when (val result = authService.authenticate(token)) {
            is Either.Left -> call.respond(HttpStatusCode.Unauthorized)
            is Either.Right -> call.attributes.put(UserKey, result.value)
        }
    }
}

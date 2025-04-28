package com.kkywalk2

import com.kkywalk2.features.auth.AuthRepository
import com.kkywalk2.features.auth.AuthService
import com.kkywalk2.features.auth.authRoutes
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    routing {
        get("/") {
            call.respondText("Hello World!")
        }

        val authRepository = AuthRepository()
        val authService = AuthService(authRepository)

        authRoutes(authService)
    }
}

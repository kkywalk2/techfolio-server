package com.kkywalk2

import com.kkywalk2.features.auth.AuthRepository
import com.kkywalk2.features.auth.AuthService
import com.kkywalk2.features.auth.authRoutes
import com.kkywalk2.features.challenge.challengeRoutes
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    val authRepository = AuthRepository()
    val authService = AuthService(authRepository)

    routing {
        get("/") {
            call.respondText("Hello World!")
        }

        authRoutes(authService)
        challengeRoutes(authService)
    }
}

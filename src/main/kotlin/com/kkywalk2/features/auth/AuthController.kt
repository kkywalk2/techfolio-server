package com.kkywalk2.features.auth

import io.ktor.server.request.*
import io.ktor.server.routing.*
import io.ktor.server.response.respond

fun Route.authRoutes(authService: AuthService) {
    post("/auth/onboard") {
        val request = call.receive<OnboardRequest>()
        val response = authService.onboard(request, System.currentTimeMillis())
        call.respond(response)
    }
}

package com.kkywalk2.features.auth

import com.kkywalk2.common.respondEither
import io.ktor.server.request.*
import io.ktor.server.routing.*

fun Route.authRoutes(authService: AuthService) {
    post("/auth/onboard") {
        val request = call.receive<OnboardRequest>()
        val response = authService.onboard(request, System.currentTimeMillis())
        call.respondEither(response)
    }
}

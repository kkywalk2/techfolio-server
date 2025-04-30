package com.kkywalk2.features.auth

import com.google.firebase.auth.FirebaseAuthException
import io.ktor.http.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.authRoutes(authService: AuthService) {
    post("/auth/onboard") {
        try {
            val request = call.receive<OnboardRequest>()
            val response = authService.onboard(request, System.currentTimeMillis())
            call.respond(HttpStatusCode.Created, response)
        } catch (e: IllegalArgumentException) {
            call.respond(HttpStatusCode.BadRequest, e.message ?: "Invalid request")
        } catch (e: FirebaseAuthException) {
            call.respond(HttpStatusCode.Unauthorized, "Invalid ID token")
        }
    }
}

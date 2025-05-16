package com.kkywalk2

import com.kkywalk2.features.user.UserRepository
import com.kkywalk2.features.user.UserService
import com.kkywalk2.features.user.userRoutes
import com.kkywalk2.features.challenge.challengeRoutes
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    val userRepository = UserRepository()
    val userService = UserService(userRepository)

    routing {
        get("/") {
            call.respondText("Hello World!")
        }

        userRoutes(userService)
        challengeRoutes(userService)
    }
}

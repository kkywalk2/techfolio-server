package com.kkywalk2.features.user

import com.kkywalk2.common.respondEither
import io.ktor.server.request.*
import io.ktor.server.routing.*

fun Route.userRoutes(userService: UserService) {
    post("/user") {
        val request = call.receive<CreateUserRequest>()
        val response = userService.create(request, System.currentTimeMillis())
        call.respondEither(response)
    }
}

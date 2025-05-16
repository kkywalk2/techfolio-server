package com.kkywalk2.features.challenge

import arrow.core.right
import com.kkywalk2.common.respondEither
import com.kkywalk2.features.user.AuthPlugin
import com.kkywalk2.features.user.UserService
import io.ktor.server.routing.*

fun Route.challengeRoutes(userService: UserService) {
    route("/challenges") {
        install(AuthPlugin) {
            this.userService = userService
        }
        post {
            call.respondEither("authenticated~".right())
        }
    }
}

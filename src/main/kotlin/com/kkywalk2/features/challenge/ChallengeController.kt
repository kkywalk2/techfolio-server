package com.kkywalk2.features.challenge

import arrow.core.right
import com.kkywalk2.common.respondEither
import com.kkywalk2.features.auth.AuthPlugin
import com.kkywalk2.features.auth.AuthService
import io.ktor.server.routing.*

fun Route.challengeRoutes(authService: AuthService) {
    route("/challenges") {
        install(AuthPlugin) {
            this.authService = authService
        }
        post {
            call.respondEither("authenticated~".right())
        }
    }
}

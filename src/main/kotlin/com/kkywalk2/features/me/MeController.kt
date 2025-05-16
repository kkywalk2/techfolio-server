package com.kkywalk2.features.me

import com.kkywalk2.features.user.AuthPlugin
import com.kkywalk2.features.user.UserKey
import com.kkywalk2.features.user.UserService
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.meRoutes(userService: UserService) {
    route("/me") {
        install(AuthPlugin) {
            this.userService = userService
        }
        get {
            call.respond(call.attributes[UserKey])
        }
    }
}

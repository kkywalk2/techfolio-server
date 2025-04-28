package com.kkywalk2

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.plugins.cors.routing.*

fun Application.configureHTTP() {
    install(CORS) {
        allowMethod(HttpMethod.Options)
        allowMethod(HttpMethod.Put)
        allowMethod(HttpMethod.Delete)
        allowMethod(HttpMethod.Patch)
        allowMethod(HttpMethod.Post)
        allowHeader(HttpHeaders.Authorization)
        allowHeader(HttpHeaders.ContentType)
        allowHeader(HttpHeaders.Accept)
        allowHeader(HttpHeaders.Host)
        allowHeader(HttpHeaders.ContentLength)
        allowHeader(HttpHeaders.UserAgent)
        allowHeader(HttpHeaders.AcceptEncoding)
        allowHeader(HttpHeaders.Connection)
        anyHost() // @TODO: Don't do this in production if possible. Try to limit it.
    }
}

package com.kkywalk2.common

import arrow.core.Either
import io.ktor.server.application.*
import io.ktor.server.response.*

suspend fun ApplicationCall.respondEither(
    either: Either<TechfolioError, Any>
) = either.fold(
    { respond(it.statusCode, it.message ?: "Unknown error") },
    { respond(it) }
)

package com.kkywalk2.common

import io.ktor.http.*

open class TechfolioError(msg: String, val statusCode: HttpStatusCode) : RuntimeException(msg)

class UnauthorizedError : TechfolioError("Invalid ID token", HttpStatusCode.Unauthorized)

class ValidationError(msg: String) : TechfolioError(msg, HttpStatusCode.BadRequest)

class NotFoundError(msg: String) : TechfolioError(msg, HttpStatusCode.NotFound)

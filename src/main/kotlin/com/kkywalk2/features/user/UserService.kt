package com.kkywalk2.features.user

import arrow.core.Either
import arrow.core.raise.either
import arrow.core.raise.ensure
import arrow.core.raise.ensureNotNull
import com.google.firebase.auth.FirebaseAuthException
import com.kkywalk2.common.TechfolioError
import com.kkywalk2.common.UnauthorizedError
import com.kkywalk2.common.ValidationError

class UserService(private val userRepository: UserRepository) {

    suspend fun create(
        request: CreateUserRequest,
        now: Long,
    ): Either<TechfolioError, CreateUserResponse> = either {
        try {
            val decodedToken = FirebaseConfig.getAuth().verifyIdToken(request.idToken)
            val uid = decodedToken.uid

            ensure(decodedToken.email == request.email) {
                ValidationError("Email mismatch")
            }

            val user = User(
                id = uid,
                email = request.email,
                name = request.name,
                createdAt = now,
            )

            userRepository.saveUser(user)
            CreateUserResponse(user.id, user.email, user.name)
        } catch (e: FirebaseAuthException) {
            raise(UnauthorizedError())
        }
    }

    suspend fun authenticate(
        idToken: String,
    ): Either<TechfolioError, User> = either {
        val decodedToken = FirebaseConfig.getAuth().verifyIdToken(idToken)
        val uid = decodedToken.uid

        val user = userRepository.findUserById(uid)

        ensureNotNull(user) {
            UnauthorizedError()
        }
    }
}

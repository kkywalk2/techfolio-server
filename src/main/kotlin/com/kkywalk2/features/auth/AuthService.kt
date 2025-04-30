package com.kkywalk2.features.auth

import arrow.core.Either
import arrow.core.raise.either
import arrow.core.raise.ensure
import com.google.firebase.auth.FirebaseAuthException
import com.kkywalk2.common.TechfolioError
import com.kkywalk2.common.UnauthorizedError
import com.kkywalk2.common.ValidationError

class AuthService(private val authRepository: AuthRepository) {

    suspend fun onboard(
        request: OnboardRequest,
        now: Long,
    ): Either<TechfolioError, OnboardResponse> = either {
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

            authRepository.saveUser(user)
            OnboardResponse(user.id, user.email, user.name)
        } catch (e: FirebaseAuthException) {
            raise(UnauthorizedError())
        }
    }
}

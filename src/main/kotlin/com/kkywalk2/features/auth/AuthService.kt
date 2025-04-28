package com.kkywalk2.features.auth

class AuthService(private val authRepository: AuthRepository) {
    suspend fun onboard(request: OnboardRequest, now: Long): OnboardResponse {
        val user = User(
            id = request.idToken,
            email = request.email,
            createdAt = now,
        )

        authRepository.saveUser(user)
        return OnboardResponse(user.id, user.email)
    }
}

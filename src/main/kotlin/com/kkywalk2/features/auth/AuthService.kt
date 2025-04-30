package com.kkywalk2.features.auth

class AuthService(private val authRepository: AuthRepository) {
    suspend fun onboard(request: OnboardRequest, now: Long): OnboardResponse {
        val decodedToken = FirebaseConfig.getAuth().verifyIdToken(request.idToken)
        val uid = decodedToken.uid

        if (decodedToken.email != request.email) {
            throw IllegalArgumentException("Email mismatch")
        }

        val user = User(
            id = uid,
            email = request.email,
            name = request.name,
            createdAt = now,
        )

        authRepository.saveUser(user)
        return OnboardResponse(user.id, user.email, user.name)
    }
}

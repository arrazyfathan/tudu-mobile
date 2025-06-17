package com.arrazyfathan.tudu.features.auth.domain.model

data class RefreshToken(
    val email: String,
    val id: String,
    val name: String,
    val username: String,
    val token: Token,
) {
    data class Token(
        val accessToken: String,
        val refreshToken: String,
    )
}

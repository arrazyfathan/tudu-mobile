package com.arrazyfathan.tudu.core.domain.auth

data class AuthInfo(
    val accessToken: String? = null,
    val refreshToken: String? = null,
    val userId: String? = null,
)

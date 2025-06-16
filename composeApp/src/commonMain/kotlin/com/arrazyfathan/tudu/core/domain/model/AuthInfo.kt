package com.arrazyfathan.tudu.core.domain.model

data class AuthInfo(
    val accessToken: String? = null,
    val refreshToken: String? = null,
    val userId: String? = null,
)

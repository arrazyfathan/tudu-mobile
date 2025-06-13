package com.arrazyfathan.tudu.features.auth.data.dto


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LoginResponseDto(
    @SerialName("email")
    val email: String? = null,
    @SerialName("id")
    val id: String? = null,
    @SerialName("name")
    val name: String? = null,
    @SerialName("token")
    val token: Token? = null,
    @SerialName("username")
    val username: String? = null
) {
    @Serializable
    data class Token(
        @SerialName("access_token")
        val accessToken: String? = null,
        @SerialName("refresh_token")
        val refreshToken: String? = null
    )
}
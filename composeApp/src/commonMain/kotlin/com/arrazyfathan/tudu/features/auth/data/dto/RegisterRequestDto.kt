package com.arrazyfathan.tudu.features.auth.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RegisterRequestDto(
    @SerialName("email") val email: String? = null,
    @SerialName("password") val password: String? = null,
    @SerialName("username") val username: String? = null,
    @SerialName("name") val name: String? = null,
)

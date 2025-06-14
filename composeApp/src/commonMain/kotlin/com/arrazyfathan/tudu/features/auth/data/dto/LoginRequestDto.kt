package com.arrazyfathan.tudu.features.auth.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LoginRequestDto(
    @SerialName("password")
    val password: String? = null,
    @SerialName("username")
    val username: String? = null,
)

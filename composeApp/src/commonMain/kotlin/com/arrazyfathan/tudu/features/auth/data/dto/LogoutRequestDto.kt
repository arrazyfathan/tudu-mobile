package com.arrazyfathan.tudu.features.auth.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LogoutRequestDto(
    @SerialName("refresh_token")
    val refreshToken: String? = null,
)

package com.arrazyfathan.tudu.features.auth.data.mapper

import com.arrazyfathan.tudu.core.data.networking.RefreshTokenResponse
import com.arrazyfathan.tudu.features.auth.data.dto.LoginResponseDto
import com.arrazyfathan.tudu.features.auth.domain.model.RefreshToken
import com.arrazyfathan.tudu.features.auth.domain.model.User

fun LoginResponseDto.toUser(): User =
    User(
        id = this.id.orEmpty(),
        name = this.name.orEmpty(),
        email = this.email.orEmpty(),
        userName = this.username.orEmpty(),
    )

fun RefreshTokenResponse.toRefreshToken(): RefreshToken =
    RefreshToken(
        email = this.email.orEmpty(),
        id = this.id.orEmpty(),
        name = this.name.orEmpty(),
        username = this.username.orEmpty(),
        token =
            RefreshToken.Token(
                accessToken = this.token?.accessToken.orEmpty(),
                refreshToken = this.token?.refreshToken.orEmpty(),
            ),
    )

package com.arrazyfathan.tudu.features.auth.data.mapper

import com.arrazyfathan.tudu.features.auth.data.dto.LoginResponseDto
import com.arrazyfathan.tudu.features.auth.domain.model.User

fun LoginResponseDto.toUser(): User {
    return User(
        id = this.id.orEmpty(),
        name = this.name.orEmpty(),
        email = this.email.orEmpty(),
        userName = this.username.orEmpty(),
    )
}

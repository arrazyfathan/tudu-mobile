package com.arrazyfathan.tudu.features.auth.domain.repository

import com.arrazyfathan.tudu.core.domain.utils.DataError
import com.arrazyfathan.tudu.core.domain.utils.EmptyResult
import com.arrazyfathan.tudu.core.domain.utils.Result
import com.arrazyfathan.tudu.features.auth.domain.model.RefreshToken
import com.arrazyfathan.tudu.features.auth.domain.model.User

interface AuthenticationRepository {
    suspend fun login(
        username: String,
        password: String,
    ): Result<User?, DataError>

    suspend fun register(
        username: String,
        password: String,
        email: String,
        name: String,
    ): EmptyResult<DataError>

    suspend fun logout(refreshToken: String): EmptyResult<DataError>

    suspend fun refreshToken(refreshToken: String): Result<RefreshToken?, DataError>
}

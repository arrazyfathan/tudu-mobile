package com.arrazyfathan.tudu.features.auth.data.repository

import com.arrazyfathan.tudu.core.data.networking.post
import com.arrazyfathan.tudu.core.domain.utils.ApiResponse
import com.arrazyfathan.tudu.core.domain.utils.DataError
import com.arrazyfathan.tudu.core.domain.utils.Result
import com.arrazyfathan.tudu.core.domain.utils.map
import com.arrazyfathan.tudu.features.auth.data.dto.LoginRequestDto
import com.arrazyfathan.tudu.features.auth.data.dto.LoginResponseDto
import com.arrazyfathan.tudu.features.auth.data.mapper.toUser
import com.arrazyfathan.tudu.features.auth.domain.model.User
import com.arrazyfathan.tudu.features.auth.domain.repository.AuthenticationRepository
import io.ktor.client.HttpClient

class AuthenticationRepositoryImpl(
    private val httpClient: HttpClient,
) : AuthenticationRepository {
    override suspend fun login(
        username: String,
        password: String,
    ): Result<User?, DataError> {
        val result =
            httpClient.post<LoginRequestDto, ApiResponse<LoginResponseDto>>(
                route = "/api/auth/login",
                body =
                    LoginRequestDto(
                        username = username,
                        password = password,
                    ),
            )

        if (result is Result.Success) {
        }

        return result.map { it.data?.toUser() }
    }

    override suspend fun register(
        username: String,
        password: String,
        email: String,
        name: String,
    ): Result<String, DataError> {
        TODO("Not yet implemented")
    }

    override suspend fun logout(): Result<String, DataError> {
        TODO("Not yet implemented")
    }

    override suspend fun refreshToken(): Result<String, DataError> {
        TODO("Not yet implemented")
    }
}

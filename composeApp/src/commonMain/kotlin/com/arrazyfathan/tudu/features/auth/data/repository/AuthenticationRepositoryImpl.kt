package com.arrazyfathan.tudu.features.auth.data.repository

import com.arrazyfathan.tudu.core.data.networking.RefreshTokenRequest
import com.arrazyfathan.tudu.core.data.networking.RefreshTokenResponse
import com.arrazyfathan.tudu.core.data.networking.authorizationHeader
import com.arrazyfathan.tudu.core.data.networking.post
import com.arrazyfathan.tudu.core.data.networking.token
import com.arrazyfathan.tudu.core.domain.model.AuthInfo
import com.arrazyfathan.tudu.core.domain.utils.ApiResponse
import com.arrazyfathan.tudu.core.domain.utils.DataError
import com.arrazyfathan.tudu.core.domain.utils.EmptyResult
import com.arrazyfathan.tudu.core.domain.utils.Result
import com.arrazyfathan.tudu.core.domain.utils.asEmptyDataResult
import com.arrazyfathan.tudu.core.domain.utils.map
import com.arrazyfathan.tudu.core.preferences.AuthPreferences
import com.arrazyfathan.tudu.features.auth.data.dto.LoginRequestDto
import com.arrazyfathan.tudu.features.auth.data.dto.LoginResponseDto
import com.arrazyfathan.tudu.features.auth.data.dto.LogoutRequestDto
import com.arrazyfathan.tudu.features.auth.data.dto.RegisterRequestDto
import com.arrazyfathan.tudu.features.auth.data.mapper.toRefreshToken
import com.arrazyfathan.tudu.features.auth.data.mapper.toUser
import com.arrazyfathan.tudu.features.auth.domain.model.RefreshToken
import com.arrazyfathan.tudu.features.auth.domain.model.User
import com.arrazyfathan.tudu.features.auth.domain.repository.AuthenticationRepository
import io.ktor.client.HttpClient

class AuthenticationRepositoryImpl(
    private val httpClient: HttpClient,
    private val authPreferences: AuthPreferences,
) : AuthenticationRepository {
    override suspend fun login(
        username: String,
        password: String,
    ): Result<User?, DataError> {
        val response =
            httpClient.post<LoginRequestDto, ApiResponse<LoginResponseDto>>(
                route = "/api/auth/login",
                body =
                    LoginRequestDto(
                        username = username,
                        password = password,
                    ),
            )

        if (response is Result.Success) {
            val loginData = response.result.data
            val authInfo =
                AuthInfo(
                    accessToken = loginData?.token?.accessToken,
                    refreshToken = loginData?.token?.refreshToken,
                    userId = loginData?.id,
                )
            authPreferences.save(authInfo)
        }

        return response.map { it.data?.toUser() }
    }

    override suspend fun register(
        username: String,
        password: String,
        email: String,
        name: String,
    ): EmptyResult<DataError> {
        val response =
            httpClient.post<RegisterRequestDto, Unit>(
                route = "/api/auth/register",
                body =
                    RegisterRequestDto(
                        username = username,
                        password = password,
                        email = email,
                        name = name,
                    ),
            )

        return response.asEmptyDataResult()
    }

    override suspend fun logout(refreshToken: String): EmptyResult<DataError> {
        val response =
            httpClient.post<LogoutRequestDto, Unit>(
                route = "/api/auth/logout",
                body =
                    LogoutRequestDto(
                        refreshToken = refreshToken,
                    ),
                headers =
                    mapOf(
                        authorizationHeader(authPreferences.token()),
                    ),
            )

        return response.asEmptyDataResult()
    }

    override suspend fun refreshToken(refreshToken: String): Result<RefreshToken?, DataError> {
        val response =
            httpClient.post<RefreshTokenRequest, ApiResponse<RefreshTokenResponse>>(
                route = "/api/auth/refresh_token",
                body =
                    RefreshTokenRequest(
                        refreshToken = refreshToken,
                    ),
            )
        return response.map { it.data?.toRefreshToken() }
    }
}

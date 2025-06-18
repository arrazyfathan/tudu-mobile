package com.arrazyfathan.tudu.core.data.networking

import com.arrazyfathan.tudu.core.domain.model.AuthInfo
import com.arrazyfathan.tudu.core.domain.utils.ApiResponse
import com.arrazyfathan.tudu.core.domain.utils.Result
import com.arrazyfathan.tudu.core.preferences.SessionStorage
import com.arrazyfathan.tudu.utils.PrettyLogger
import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.HttpRequestRetry
import io.ktor.client.plugins.HttpResponseValidator
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.auth.Auth
import io.ktor.client.plugins.auth.providers.BearerTokens
import io.ktor.client.plugins.auth.providers.bearer
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.serialization.json.Json

class HttpClientFactory(
    private val sessionStorage: SessionStorage,
) {
    private val mutex = Mutex()

    fun build(engine: HttpClientEngine): HttpClient =
        HttpClient(engine) {
            install(ContentNegotiation) {
                json(
                    json =
                        Json {
                            ignoreUnknownKeys = true
                            isLenient = true
                            explicitNulls = false
                        },
                )
            }
            install(HttpRequestRetry) {
                retryOnServerErrors(maxRetries = 3)
                exponentialDelay()
            }
            install(HttpTimeout) {
                socketTimeoutMillis = 60_000L
                requestTimeoutMillis = 60_000L
            }
            install(Logging) {
                logger = PrettyLogger
                level = LogLevel.ALL
            }
            install(Auth) {
                bearer {
                    loadTokens {
                        val authInfo = sessionStorage.getAuthInfo() ?: return@loadTokens null

                        BearerTokens(
                            accessToken = authInfo.accessToken.orEmpty(),
                            refreshToken = authInfo.refreshToken.orEmpty(),
                        )
                    }
                    refreshTokens {
                        mutex.withLock {
                            val authInfo = sessionStorage.getAuthInfo() ?: return@withLock null

                            val response =
                                client.post<RefreshTokenRequest, ApiResponse<RefreshTokenResponse>>(
                                    route = "/api/auth/refresh_token",
                                    body =
                                        RefreshTokenRequest(
                                            refreshToken = authInfo.refreshToken.orEmpty(),
                                        ),
                                )

                            if (response is Result.Success) {
                                val data = response.result.data
                                val newAuthInfo =
                                    AuthInfo(
                                        accessToken = data?.token?.accessToken,
                                        refreshToken = data?.token?.refreshToken,
                                        userId = data?.id,
                                    )

                                sessionStorage.save(newAuthInfo)

                                BearerTokens(
                                    accessToken = newAuthInfo.accessToken.orEmpty(),
                                    refreshToken = newAuthInfo.refreshToken,
                                )
                            } else {
                                BearerTokens(
                                    accessToken = "",
                                    refreshToken = "",
                                )
                            }
                        }
                    }
                }
            }
            HttpResponseValidator {
                handleResponseExceptionWithRequest { exception, _ ->
                    if (exception is ClientRequestException && exception.response.status.value == 401) {
                        sessionStorage.clear()
                    }
                }
            }
            defaultRequest {
                contentType(ContentType.Application.Json)
            }
        }
}

package com.arrazyfathan.tudu.features.home.domain.usecase

import com.arrazyfathan.tudu.core.domain.usecase.UseCase
import com.arrazyfathan.tudu.core.domain.utils.DataError
import com.arrazyfathan.tudu.core.domain.utils.Result
import com.arrazyfathan.tudu.features.auth.domain.repository.AuthenticationRepository

class LogoutUseCase(
    private val authenticationRepository: AuthenticationRepository,
) : UseCase<LogoutUseCase.Request, Unit> {
    data class Request(
        val refreshToken: String,
    )

    override suspend fun invoke(request: Request): Result<Unit?, DataError> = authenticationRepository.logout(request.refreshToken)
}

package com.arrazyfathan.tudu.features.auth.domain.usecase

import com.arrazyfathan.tudu.core.domain.usecase.UseCase
import com.arrazyfathan.tudu.core.domain.utils.DataError
import com.arrazyfathan.tudu.core.domain.utils.EmptyResult
import com.arrazyfathan.tudu.features.auth.domain.repository.AuthenticationRepository

class RegisterUseCase(
    private val repository: AuthenticationRepository,
) : UseCase<RegisterUseCase.Request, Unit> {
    data class Request(
        val username: String,
        val password: String,
        val email: String,
        val name: String,
    )

    override suspend fun invoke(request: Request): EmptyResult<DataError> =
        repository.register(
            username = request.username,
            password = request.password,
            email = request.email,
            name = request.name,
        )
}

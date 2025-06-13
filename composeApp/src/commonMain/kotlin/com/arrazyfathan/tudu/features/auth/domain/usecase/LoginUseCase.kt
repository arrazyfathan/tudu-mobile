package com.arrazyfathan.tudu.features.auth.domain.usecase

import com.arrazyfathan.tudu.core.domain.utils.DataError
import com.arrazyfathan.tudu.core.domain.utils.Result
import com.arrazyfathan.tudu.core.domain.utils.UseCase
import com.arrazyfathan.tudu.features.auth.domain.model.User
import com.arrazyfathan.tudu.features.auth.domain.repository.AuthenticationRepository

class LoginUseCase(
    private val repository: AuthenticationRepository
) : UseCase<LoginUseCase.Request, User> {

    data class Request(
        val username: String, val password: String
    )

    override suspend fun invoke(request: Request): Result<User, DataError> {
        return repository.login(request.username, request.password)
    }


}
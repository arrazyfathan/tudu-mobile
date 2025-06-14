package com.arrazyfathan.tudu.features.auth.data

import com.arrazyfathan.tudu.core.domain.utils.DataError
import com.arrazyfathan.tudu.core.domain.utils.Result
import com.arrazyfathan.tudu.features.auth.domain.model.User
import com.arrazyfathan.tudu.features.auth.domain.repository.AuthenticationRepository

class FakeAuthRepository : AuthenticationRepository {
    override suspend fun login(username: String, password: String): Result<User?, DataError> {
        val user = User(
            id = "demo-user-id",
            name = "Demo User",
            userName = username,
            email = "demo@example.com"
        )
        return Result.Success(user)
    }

    override suspend fun register(
        username: String, password: String, email: String, name: String
    ): Result<String, DataError> {
        return Result.Success("Registered successfully")
    }

    override suspend fun logout(): Result<String, DataError> {
        return Result.Success("Logged out successfully")
    }

    override suspend fun refreshToken(): Result<String, DataError> {
        return Result.Success("new-demo-token")
    }

}
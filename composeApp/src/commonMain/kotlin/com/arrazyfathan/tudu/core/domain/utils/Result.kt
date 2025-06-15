package com.arrazyfathan.tudu.core.domain.utils

import kotlinx.serialization.Serializable

sealed interface Result<out D, out E : Error> {
    data class Success<out D>(
        val result: D,
    ) : Result<D, Nothing>

    data class Error<out E : com.arrazyfathan.tudu.core.domain.utils.Error>(
        val error: E,
    ) : Result<Nothing, E>
}

inline fun <T, E : Error, R> Result<T, E>.map(map: (T) -> R): Result<R, E> =
    when (this) {
        is Result.Error -> Result.Error(error)
        is Result.Success -> Result.Success(map(result))
    }

fun <T, E : Error> Result<T, E>.asEmptyDataResult(): EmptyResult<E> = map { }

inline fun <T, E : Error> Result<T, E>.onSuccess(action: (T?) -> Unit): Result<T, E> =
    when (this) {
        is Result.Error -> this
        is Result.Success -> {
            action(result)
            this
        }
    }

inline fun <T, E : Error> Result<T, E>.onError(action: (E) -> Unit): Result<T, E> =
    when (this) {
        is Result.Error -> {
            action(error)
            this
        }

        is Result.Success -> this
    }

typealias EmptyResult<E> = Result<Unit, E>

@Serializable
data class ApiResponse<out T>(
    val status: String,
    val message: String,
    val data: T? = null,
)

@Serializable
data class ErrorResponse(
    val status: String,
    val message: String,
    val errors: Map<String, String>? = null,
)

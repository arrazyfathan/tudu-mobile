package com.arrazyfathan.tudu.core.domain.utils

sealed class DataError(
    open val message: String? = null,
    open val code: Int? = null,
) : Error {
    data class Client(
        override val message: String,
        override val code: Int,
    ) : DataError(message, code)

    data class ValidationError(
        val errors: Map<String, String>,
    ) : DataError("Validation failed", 400)

    data object Unauthorized : DataError()

    data object ServerError : DataError()

    data object NetworkError : DataError()

    data object SerializationError : DataError()

    data object RequestTimeoutError : DataError()

    data object TooManyRequestError : DataError()

    data object DiskFullError : DataError()

    data object UnknownError : DataError()
}

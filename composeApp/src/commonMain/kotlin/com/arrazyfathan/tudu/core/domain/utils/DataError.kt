package com.arrazyfathan.tudu.core.domain.utils

sealed class DataError(
    open val message: String, open val code: Int? = null
) : Error {
    data class Http(override val message: String, override val code: Int) : DataError(message, code)
    data class ServerError(override val message: String, override val code: Int) :
        DataError(message, code)

    data class NetworkError(override val message: String) : DataError(message)
    data class SerializationError(override val message: String) : DataError(message)
    data class RequestTimeoutError(override val message: String) : DataError(message)
    data class TooManyRequestError(override val message: String) : DataError(message)
    data class DiskFullError(override val message: String) : DataError(message)
    data class UnknownError(override val message: String) : DataError(message)
}

package com.arrazyfathan.tudu.core.presentation

import com.arrazyfathan.tudu.core.domain.utils.DataError
import tudu.composeapp.generated.resources.Res
import tudu.composeapp.generated.resources.error_client
import tudu.composeapp.generated.resources.error_disk_full
import tudu.composeapp.generated.resources.error_no_internet
import tudu.composeapp.generated.resources.error_request_timeout
import tudu.composeapp.generated.resources.error_serialization
import tudu.composeapp.generated.resources.error_too_many_request
import tudu.composeapp.generated.resources.error_unauthorized
import tudu.composeapp.generated.resources.error_unknown
import tudu.composeapp.generated.resources.error_validation

fun DataError.toUiText(): UiText {
    val stringRes =
        when (this) {
            is DataError.DiskFullError -> Res.string.error_disk_full
            is DataError.Client -> Res.string.error_client
            is DataError.Unauthorized -> Res.string.error_unauthorized
            is DataError.ValidationError -> Res.string.error_validation
            is DataError.NetworkError -> Res.string.error_no_internet
            is DataError.RequestTimeoutError -> Res.string.error_request_timeout
            is DataError.SerializationError -> Res.string.error_serialization
            is DataError.ServerError -> Res.string.error_unknown
            is DataError.TooManyRequestError -> Res.string.error_too_many_request
            is DataError.UnknownError -> Res.string.error_unknown
        }

    return UiText.StringResourceId(stringRes)
}

fun DataError.toErrorMessage(): UiText {
    val message =
        when (this) {
            is DataError.DiskFullError -> this.message ?: "Oops, it seems like your disk is full."
            is DataError.Client -> this.message
            is DataError.ValidationError -> {
                this.errors.entries.joinToString("\n") { (field, error) -> "$field: $error" }
            }
            is DataError.Unauthorized -> this.message ?: "Unauthorized"
            is DataError.NetworkError -> this.message ?: "Network Error"
            is DataError.RequestTimeoutError -> this.message ?: "Request Timeout"
            is DataError.SerializationError -> this.message ?: "Oops, something went wrong"
            is DataError.ServerError -> this.message ?: "Oops, something went wrong"
            is DataError.TooManyRequestError -> this.message ?: "Too many request"
            is DataError.UnknownError -> this.message ?: "Oops, something went wrong"
        }
    return UiText.DynamicString(message)
}

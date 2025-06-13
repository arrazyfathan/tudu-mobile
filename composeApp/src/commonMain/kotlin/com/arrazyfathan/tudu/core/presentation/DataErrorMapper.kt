package com.arrazyfathan.tudu.core.presentation

import com.arrazyfathan.tudu.core.domain.utils.DataError
import tudu.composeapp.generated.resources.Res
import tudu.composeapp.generated.resources.error_disk_full
import tudu.composeapp.generated.resources.error_http
import tudu.composeapp.generated.resources.error_no_internet
import tudu.composeapp.generated.resources.error_request_timeout
import tudu.composeapp.generated.resources.error_serialization
import tudu.composeapp.generated.resources.error_too_many_request
import tudu.composeapp.generated.resources.error_unknown

fun DataError.toUiText(): UiText {
    val stringRes = when (this) {

        is DataError.DiskFullError -> Res.string.error_disk_full
        is DataError.Http -> Res.string.error_http
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
    val message = when (this) {
        is DataError.DiskFullError -> this.message
        is DataError.Http -> this.message
        is DataError.NetworkError -> this.message
        is DataError.RequestTimeoutError -> this.message
        is DataError.SerializationError -> this.message
        is DataError.ServerError -> this.message
        is DataError.TooManyRequestError -> this.message
        is DataError.UnknownError -> this.message
    }
    return UiText.DynamicString(message)
}
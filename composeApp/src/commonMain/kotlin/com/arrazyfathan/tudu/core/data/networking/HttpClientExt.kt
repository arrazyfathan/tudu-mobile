package com.arrazyfathan.tudu.core.data.networking

import com.arrazyfathan.tudu.core.domain.utils.DataError
import com.arrazyfathan.tudu.core.domain.utils.ErrorResponse
import com.arrazyfathan.tudu.core.domain.utils.Result
import com.arrazyfathan.tudu.core.preferences.SessionStorage
import io.ktor.client.HttpClient
import io.ktor.client.call.NoTransformationFoundException
import io.ktor.client.call.body
import io.ktor.client.network.sockets.SocketTimeoutException
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.parameter
import io.ktor.client.request.patch
import io.ktor.client.request.post
import io.ktor.client.request.put
import io.ktor.client.request.setBody
import io.ktor.client.request.url
import io.ktor.client.statement.HttpResponse
import io.ktor.util.network.UnresolvedAddressException
import kotlinx.coroutines.ensureActive
import kotlin.coroutines.coroutineContext

expect val BASE_URL: String

suspend inline fun <reified Response : Any> HttpClient.get(
    route: String,
    queryParameters: Map<String, Any?> = mapOf(),
    headers: Map<String, String> = mapOf(),
): Result<Response, DataError> =
    safeCall {
        get {
            url(constructRoute(route))
            queryParameters.forEach { (key, value) ->
                parameter(key, value)
            }
            headers.forEach { (key, value) ->
                header(key, value)
            }
        }
    }

suspend inline fun <reified Request, reified Response : Any> HttpClient.post(
    route: String,
    body: Request,
    headers: Map<String, String> = mapOf(),
): Result<Response, DataError> =
    safeCall {
        post {
            url(constructRoute(route))
            setBody(body)
            headers.forEach { (key, value) ->
                header(key, value)
            }
        }
    }

suspend inline fun <reified Response : Any> HttpClient.delete(
    route: String,
    queryParameters: Map<String, Any?> = mapOf(),
    headers: Map<String, String> = mapOf(),
): Result<Response, DataError> =
    safeCall {
        delete {
            url(constructRoute(route))
            queryParameters.forEach { (key, value) ->
                parameter(key, value)
            }
            headers.forEach { (key, value) ->
                header(key, value)
            }
        }
    }

suspend inline fun <reified Request, reified Response : Any> HttpClient.put(
    route: String,
    body: Request,
    headers: Map<String, String> = mapOf(),
): Result<Response, DataError> =
    safeCall {
        put {
            url(constructRoute(route))
            setBody(body)
            headers.forEach { (key, value) ->
                header(key, value)
            }
        }
    }

suspend inline fun <reified Request, reified Response : Any> HttpClient.patch(
    route: String,
    body: Request,
    headers: Map<String, String> = mapOf(),
): Result<Response, DataError> =
    safeCall {
        patch {
            url(constructRoute(route))
            setBody(body)
            headers.forEach { (key, value) ->
                header(key, value)
            }
        }
    }

suspend inline fun <reified T> safeCall(execute: () -> HttpResponse): Result<T, DataError> {
    val response =
        try {
            execute()
        } catch (e: SocketTimeoutException) {
            return Result.Error(DataError.RequestTimeoutError)
        } catch (e: UnresolvedAddressException) {
            return Result.Error(DataError.NetworkError)
        } catch (e: Exception) {
            coroutineContext.ensureActive()
            return Result.Error(DataError.UnknownError)
        }

    return responseToResult(response)
}

suspend inline fun <reified T> responseToResult(response: HttpResponse): Result<T, DataError> {
    val responseBody = response.body<T>()
    return when (val code = response.status.value) {
        in 200..299 -> {
            try {
                Result.Success(responseBody)
            } catch (e: NoTransformationFoundException) {
                Result.Error(DataError.SerializationError)
            }
        }

        400 -> {
            try {
                val errorResponse = response.body<ErrorResponse>()
                if (errorResponse.errors != null) {
                    Result.Error(DataError.ValidationError(errorResponse.errors))
                } else {
                    Result.Error(DataError.Client(errorResponse.message, code))
                }
            } catch (e: Exception) {
                Result.Error(DataError.SerializationError)
            }
        }

        408 -> Result.Error(DataError.RequestTimeoutError)
        429 -> Result.Error(DataError.TooManyRequestError)
        in 401..499 -> {
            try {
                val errorResponse = response.body<ErrorResponse>()
                Result.Error(DataError.Client(errorResponse.message, code))
            } catch (e: Exception) {
                Result.Error(DataError.SerializationError)
            }
        }

        401 -> Result.Error(DataError.Unauthorized)
        in 500..599 -> Result.Error(DataError.ServerError)
        else -> Result.Error(DataError.UnknownError)
    }
}

fun constructRoute(route: String): String =
    when {
        route.contains(BASE_URL) -> route
        route.startsWith("/") -> BASE_URL + route
        else -> "$BASE_URL/$route"
    }

suspend fun SessionStorage.token(): String {
    return this.getAuthInfo()?.accessToken ?: ""
}

fun authorizationHeader(value: String): Pair<String, String> {
    return "Authorization" to "Bearer $value"
}

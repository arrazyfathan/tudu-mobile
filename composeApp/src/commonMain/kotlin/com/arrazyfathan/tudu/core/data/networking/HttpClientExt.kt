package com.arrazyfathan.tudu.core.data.networking

import com.arrazyfathan.tudu.core.domain.utils.DataError
import com.arrazyfathan.tudu.core.domain.utils.Result
import io.ktor.client.HttpClient
import io.ktor.client.call.NoTransformationFoundException
import io.ktor.client.call.body
import io.ktor.client.network.sockets.SocketTimeoutException
import io.ktor.client.request.delete
import io.ktor.client.request.get
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

const val BASE_URL = "http://10.0.2.2:3000"

suspend inline fun <reified Response : Any> HttpClient.get(
    route: String, queryParameters: Map<String, Any?> = mapOf()
): Result<Response, DataError> {
    return safeCall {
        get {
            url(constructRoute(route))
            queryParameters.forEach { (key, value) ->
                parameter(key, value)
            }
        }
    }
}

suspend inline fun <reified Request, reified Response : Any> HttpClient.post(
    route: String, body: Request
): Result<Response, DataError> {
    return safeCall {
        post {
            url(constructRoute(route))
            setBody(body)
        }
    }
}


suspend inline fun <reified Response : Any> HttpClient.delete(
    route: String, queryParameters: Map<String, Any?> = mapOf()
): Result<Response, DataError> {
    return safeCall {
        delete {
            url(constructRoute(route))
            queryParameters.forEach { (key, value) ->
                parameter(key, value)
            }
        }
    }
}

suspend inline fun <reified Request, reified Response : Any> HttpClient.put(
    route: String, body: Request
): Result<Response, DataError> {
    return safeCall {
        put {
            url(constructRoute(route))
            setBody(body)
        }
    }
}

suspend inline fun <reified Request, reified Response : Any> HttpClient.patch(
    route: String, body: Request
): Result<Response, DataError> {
    return safeCall {
        patch {
            url(constructRoute(route))
            setBody(body)
        }
    }
}

suspend inline fun <reified T> safeCall(execute: () -> HttpResponse): Result<T, DataError> {
    val response = try {
        execute()
    } catch (e: SocketTimeoutException) {
        return Result.Error(DataError.RequestTimeoutError(e.message.toString()))
    } catch (e: UnresolvedAddressException) {
        return Result.Error(DataError.NetworkError(e.message.toString()))
    } catch (e: Exception) {
        coroutineContext.ensureActive()
        return Result.Error(DataError.UnknownError(e.message.toString()))
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
                Result.Error(DataError.SerializationError(e.message))
            }
        }

        408 -> Result.Error(DataError.RequestTimeoutError("Request Timeout"))
        429 -> Result.Error(DataError.TooManyRequestError("Too Many Request"))
        in 500..599 -> Result.Error(DataError.ServerError("Server Error", code))
        else -> Result.Error(DataError.UnknownError("Unknown Error"))
    }
}

fun constructRoute(route: String): String {
    return when {
        route.contains(BASE_URL) -> route
        route.startsWith("/") -> BASE_URL + route
        else -> "$BASE_URL/$route"
    }
}

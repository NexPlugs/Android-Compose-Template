package com.example.core.network.utils

import com.example.core.network.NetworkInitializer
import com.example.core.network.response.ApiResponse
import kotlinx.coroutines.CancellationException
import retrofit2.Response


/**
 * Executes a network call and wraps the result in an [ApiResponse].
 *
 * @param T The type of the successful response body.
 * @param successCodeRange The range of HTTP status codes considered as successful responses.
 * @param f A suspend function that performs the network call and returns a [Response] of type [T].
 * @return An [ApiResponse] representing either a successful response or a failure.
 */
suspend inline fun<reified  T> responseOf(
    successCodeRange: IntRange = NetworkInitializer.successCodeRange,
    crossinline f: suspend () -> Response<T>
): ApiResponse<T> = try {
    val response = f()
    val code = response.code()
    if (code in successCodeRange) {
        ApiResponse.Success(
            code = code,
            data = response.body() ?: Unit as T
        )
    } else {
        ApiResponse.Failure.Error(code = code, message = response.message())
    }
} catch (e: CancellationException) {
    throw e
} catch (e: Throwable) {
    ApiResponse.Failure.Exception(throwable = e)
}


/**
 * Executes a network call and wraps the result in an [ApiResponse].
 *
 * @param T The type of the successful response body.
 * @param successCodeRange The range of HTTP status codes considered as successful responses.
 * @param f A suspend function that performs the network call and returns a [Response] of type [T].
 * @return An [ApiResponse] representing either a successful response or a failure.
 */
suspend inline fun<reified T> ApiResponse.Companion.apiResponseOf(
    successCodeRange: IntRange = NetworkInitializer.successCodeRange,
    crossinline f: suspend () -> Response<T>
): ApiResponse<T> = responseOf(successCodeRange, f)

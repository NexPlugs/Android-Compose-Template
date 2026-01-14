package com.example.core.network.internal

import com.example.core.network.response.ApiResponse
import com.example.core.network.utils.apiResponseOf
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.awaitResponse
import java.lang.reflect.Type

/**
 * A CallAdapter that adapts a Call<T> to a Deferred<ApiResponse<T>>.
 * @param T The type of the successful response body.
 * @param resultType The type of the result.
 * @param coroutineScope The CoroutineScope in which to execute the call.
 */
internal class ApiResponseDeferredCallAdapter<T>(
    private val resultType: Type,
    private val  coroutineScope: CoroutineScope,
): CallAdapter<T, Deferred<ApiResponse<T>>> {


    override fun responseType(): Type = resultType

    @Suppress("DeferredIsResult")
    override fun adapt(call: Call<T>): Deferred<ApiResponse<T>> {
        val deferred = CompletableDeferred<ApiResponse<T>>().apply {
            invokeOnCompletion {
                if (isCancelled && !call.isCanceled) {
                    call.cancel()
                }
            }
        }

        coroutineScope.launch {
            try {
                val response = call.awaitResponse()
                val apiResponse = ApiResponse.apiResponseOf { response }
                deferred.complete(apiResponse)
            } catch (e: Throwable) {
                deferred.complete(ApiResponse.Failure.Exception(throwable = e))
            }
        }

        return deferred
    }

}
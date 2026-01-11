package com.example.core.network.internal

import com.example.core.network.internal.base.CallDelegate
import com.example.core.network.response.ApiResponse
import com.example.core.network.utils.apiResponseOf
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * A Call delegate that adapts a Call<T> to a Call<ApiResponse<T>>.
 * @param T The type of the successful response body.
 * @param proxy The original Call<T> to be adapted.
 * @param coroutineScope The CoroutineScope in which to execute the call.
 */
internal class ApiResponseCallDelegate<T>(
    proxy: Call<T>,
    private val coroutineScope: CoroutineScope
): CallDelegate<T, ApiResponse<T>>(proxy) {

    override fun enqueueImpl(callback: Callback<ApiResponse<T>>) {
        coroutineScope.launch {
            try {
                val response = proxy.execute()
                val apiResponse = ApiResponse.apiResponseOf { response }
                callback.onResponse(
                    this@ApiResponseCallDelegate,
                    Response.success(apiResponse)
                )
            } catch (e: Throwable) {
                callback.onResponse(
                    this@ApiResponseCallDelegate,
                    Response.success(ApiResponse.Failure.Exception(throwable = e))
                )
            }
        }
    }

    override fun executeImpl(): Response<ApiResponse<T>> =
        runBlocking(coroutineScope.coroutineContext) {
            val response = proxy.execute()
            val apiResponse = ApiResponse.apiResponseOf { response }
            Response.success(apiResponse)
        }


    override fun cloneImpl(): Call<ApiResponse<T>> = ApiResponseCallDelegate(
        proxy.clone(),
        coroutineScope
    )

}
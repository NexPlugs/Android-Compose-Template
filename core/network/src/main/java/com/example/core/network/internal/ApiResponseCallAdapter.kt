package com.example.core.network.internal

import kotlinx.coroutines.CoroutineScope
import retrofit2.Call
import retrofit2.CallAdapter
import java.lang.reflect.Type

/**
 * A CallAdapter that adapts a Call<T> to a Call<ApiResponse<T>>.
 * @param Type The type of the successful response body.
 * @param resultType The type of the result.
 * @param coroutineScope The CoroutineScope in which to execute the call.
 */
internal class ApiResponseCallAdapter(
    private val resultType: Type,
    private val coroutineScope: CoroutineScope
): CallAdapter<Type, ApiResponseCallDelegate<*>> {
    override fun responseType(): Type = resultType

    override fun adapt(call: Call<Type?>): ApiResponseCallDelegate<*> = ApiResponseCallDelegate(
        call,
        coroutineScope
    )

}
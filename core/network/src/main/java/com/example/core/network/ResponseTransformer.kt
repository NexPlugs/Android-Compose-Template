package com.example.core.network

import android.util.Log
import com.example.core.network.operators.ApiResponseOperator
import com.example.core.network.operators.ApiResponseSuspendOperator
import com.example.core.network.response.ApiResponse


/** Applies the given [apiResponseOperator] to the [ApiResponse] */
fun <T, V : ApiResponseOperator<T>> ApiResponse<T>.operator(
    apiResponseOperator: V,
): ApiResponse<T> = apply {
    when (this) {
        is ApiResponse.Success -> apiResponseOperator.onSuccess(this)
        is ApiResponse.Failure.Error -> apiResponseOperator.onError(this)
        is ApiResponse.Failure.Exception -> apiResponseOperator.onException(this)
    }
}



/** Applies the given [apiResponseOperator] to the [ApiResponse] */
@SuspendFunction
suspend fun <T, V : ApiResponseSuspendOperator<T>> ApiResponse<T>.suspendOperator(
    apiResponseOperator: V,
): ApiResponse<T> = apply {
    when (this) {
        is ApiResponse.Success -> apiResponseOperator.onSuccess(this)
        is ApiResponse.Failure.Error -> apiResponseOperator.onError(this)
        is ApiResponse.Failure.Exception -> apiResponseOperator.onException(this)
    }
}
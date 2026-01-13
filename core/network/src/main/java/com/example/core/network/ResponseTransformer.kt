package com.example.core.network

import android.util.StatsLog
import com.example.core.network.operators.ApiResponseOperator
import com.example.core.network.operators.ApiResponseSuspendOperator
import com.example.core.network.response.ApiResponse
import com.example.core.network.response.mapper.base.ApiSuccessResponseMapper
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract


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

/** Returns the message from the ApiResponse.Failure */
fun<T> ApiResponse.Failure<T>.message(): String {
    return when(this) {
        is ApiResponse.Failure.Error -> message()
        is ApiResponse.Failure.Exception -> message()
    }
}

/** Returns the message from the ApiResponse.Failure.Error */
fun ApiResponse.Failure.Error.message() : String {
    return this.message()
}

/** Returns the message from the ApiResponse.Failure.Exception */
fun ApiResponse.Failure.Exception.message() : String {
    return this.throwable.message.orEmpty()
}


/** Returns the StatusCode from the ApiResponse.Failure.Error */
val ApiResponse.Failure.Error.statusCode: StatusCode
    inline get() = StatusCode.fromCode(this.code)


/** Returns the StatusCode from the ApiResponse.Success */
val<T> ApiResponse.Success<T>.statusCode: StatusCode
    inline get() = StatusCode.fromCode(this.code)

/**
 *  Executes the given [onResult] block if the [ApiResponse] is a [ApiResponse.Success],
 * mapping the response using the provided [mapper].
 * Returns the original [ApiResponse] regardless of its type.
 */
@OptIn(ExperimentalContracts::class)
@Suppress("WRONG_INVOCATION_KIND")
@SuspendFunction
suspend inline fun<T, V> ApiResponse<T>.suspendOnSuccess(
    mapper: ApiSuccessResponseMapper<T, V>,
    crossinline onResult: suspend V.() -> Unit
): ApiResponse<T> {
    contract { callsInPlace(onResult, InvocationKind.EXACTLY_ONCE) }

    if(this is ApiResponse.Success) {
        onResult(mapper.map(this))
    }
    return this
}
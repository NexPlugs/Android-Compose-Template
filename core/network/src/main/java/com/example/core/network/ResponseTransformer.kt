package com.example.core.network

import com.example.core.network.operators.ApiResponseOperator
import com.example.core.network.operators.ApiResponseSuspendOperator
import com.example.core.network.response.ApiResponse
import com.example.core.network.response.mapper.base.ApiErrorResponseMapper
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
 * Executes the given [onResult] block if the [ApiResponse] is a [ApiResponse.Success],
 * mapping the response using the provided [mapper].
 * Returns the original [ApiResponse] regardless of its type.
 */
@OptIn(ExperimentalContracts::class)
@SuspendFunction
suspend inline fun<T, V> ApiResponse<T>.suspendOnSuccess(
    mapper: ApiSuccessResponseMapper<T, V>,
    crossinline onResult: suspend V.() -> Unit
): ApiResponse<T> {
    contract { callsInPlace(onResult, InvocationKind.AT_MOST_ONCE) }

    if(this is ApiResponse.Success) {
        mapper.map(this).onResult()
    }
    return this
}

/**
 * Executes the given [onResult] block if the [ApiResponse] is a [ApiResponse.Failure],
 * returning the original [ApiResponse] regardless of its type.
 */
@OptIn(ExperimentalContracts::class)
inline fun<T> ApiResponse<T>.onFailure(
    crossinline onResult: ApiResponse.Failure<T>.() -> Unit
): ApiResponse<T> {

    contract { callsInPlace(onResult, InvocationKind.AT_MOST_ONCE) }

    if(this is ApiResponse.Failure) {
        this.onResult()
    }
    return this
}


/**
 * Executes the given [onResult] block if the [ApiResponse] is a [ApiResponse.Failure.Error],
 * returning the original [ApiResponse] regardless of its type.
 */
inline fun<T> ApiResponse<T>.onError(
    crossinline  onResult: ApiResponse.Failure.Error.() -> Unit
): ApiResponse<T> {
    if(this is ApiResponse.Failure.Error) {
        this.onResult()
    }
    return this
}

/**
 * Executes the given [onResult] block if the [ApiResponse] is a [ApiResponse.Failure.Exception],
 * returning the original [ApiResponse] regardless of its type.
 */
inline fun<T> ApiResponse<T>.onException(
    crossinline  onResult: ApiResponse.Failure.Exception.() -> Unit
): ApiResponse<T> {
    if(this is ApiResponse.Failure.Exception) {
        this.onResult()
    }
    return this
}



/** Maps the [ApiResponse.Failure.Error] using the provided [mapper] */
@OptIn(ExperimentalContracts::class)
inline fun<T> ApiResponse.Failure.Error.map(
    mapper: ApiErrorResponseMapper<T>,
    crossinline onMapped: T.() -> Unit
) {
    contract { callsInPlace(onMapped, InvocationKind.AT_MOST_ONCE) }
    val mapped = mapper.map(this)
    mapped.onMapped()
}

/** Maps the [ApiResponse.Failure.Exception] using the provided [mapper] */
@OptIn(ExperimentalContracts::class)
inline fun<T> ApiResponse.Failure.Exception.map(
    mapper: ApiErrorResponseMapper<T>,
    crossinline onMapped: T.() -> Unit
) {
    contract { callsInPlace(onMapped, InvocationKind.AT_MOST_ONCE) }

    val mapped = mapper.map(this)
    mapped.onMapped()
}

/**
 * Maps the [ApiResponse] to a [Result] using the provided lambdas for each case.
 * @param onResult Lambda to transform the successful result.
 * @param onError Lambda to transform the error response into a Throwable.
 * @param onException Lambda to transform the exception response into a Throwable.
 */
inline fun<T, V> ApiResponse<T>.mapResult(
    onResult: ApiResponse.Success<T>.() -> V,
    onError: ApiResponse.Failure.Error.() -> Throwable,
    onException: ApiResponse.Failure.Exception.() -> Throwable,
): Result<V> {
    return when (this) {
        is ApiResponse.Success -> Result.success(onResult(this))
        is ApiResponse.Failure.Error -> Result.failure(onError(this))
        is ApiResponse.Failure.Exception -> Result.failure(onException(this))
    }
}
package com.example.core.network.operators

import com.example.core.network.response.ApiResponse


/**
 * Abstract class representing a suspend operator for handling API responses.
 * @param T The type of the successful API response data.
 */
abstract class ApiResponseSuspendOperator<T> : NetworkOperator {

    /**
     * Operation to handle successful API responses.
     *
     * @param apiResponse The successful API response to be handled.
     */
    abstract suspend fun onSuccess(apiResponse: ApiResponse.Success<T>)

    /**
     * Operation to handle error API responses.
     *
     * @param apiResponse The error API response to be handled.
     */
    abstract suspend fun onError(apiResponse: ApiResponse.Failure.Error)

    /**
     * Operation to handle exception API responses.
     *
     * @param apiResponse The exception API response to be handled.
     */
    abstract suspend fun onException(apiResponse: ApiResponse.Failure.Exception)
}
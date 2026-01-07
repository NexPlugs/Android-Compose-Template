package com.example.core.network.operators

import com.example.core.network.response.ApiResponse

/**
 * Abstract class for handling API responses with success, error, and exception cases.
 */
abstract class ApiResponseOperator<T>: NetworkOperator {

    /**
     * Operation to handle successful API responses
     *
     * @param apiResponse The successful API response to be handled
     */
    abstract fun onSuccess(apiResponse: ApiResponse.Success<T>)

    /**
     * Operation to handle error API responses
     *
     * @param apiResponse The error API response to be handled
     */
    abstract fun onError(apiResponse: ApiResponse.Failure.Error)

    /**
     * Operation to handle exception API responses
     *
     * @param apiResponse The exception API response to be handled
     */
    abstract fun onException(apiResponse: ApiResponse.Failure.Exception)

}
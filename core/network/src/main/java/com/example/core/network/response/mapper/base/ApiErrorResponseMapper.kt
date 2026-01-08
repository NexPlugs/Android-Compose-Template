package com.example.core.network.response.mapper.base

import com.example.core.network.response.ApiResponse

/**
 * Mapper for API error responses.
 * @param T The type to which the error response will be mapped.
 */
interface ApiErrorResponseMapper<T> {

    /**
     * Maps an API error response to the specified type.
     *
     * @param apiErrorResponse The API error response to be mapped.
     * @return The mapped value of type T.
     */
    fun map(apiErrorResponse: ApiResponse.Failure.Error): T
}
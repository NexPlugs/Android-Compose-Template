package com.example.core.network.response.mapper.base

import com.example.core.network.response.ApiResponse

/**
 * Mapper for API success responses.
 * @param T The type to which the success response will be mapped.
 */
interface ApiSuccessResponseMapper<T, V> {

    /**
     * Maps an API success response to the specified type.
     *
     * @param apiSuccessResponse The API success response to be mapped.
     * @return The mapped value of type T.
     */
    fun map(apiSuccessResponse: ApiResponse.Success<T>): V
}
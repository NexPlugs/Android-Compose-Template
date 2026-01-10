package com.example.core.network.response.mapper

import com.example.core.network.message
import com.example.core.network.response.ApiResponse
import com.example.core.network.response.ApplicationErrorResponse
import com.example.core.network.response.mapper.base.ApiErrorResponseMapper
import com.example.core.network.statusCode

/**
 * Mapper for converting API error responses to application error responses.
 */
class ErrorResponseMapper : ApiErrorResponseMapper<ApplicationErrorResponse> {

    /**
     * Maps an API error response to an application error response.
     *
     * @param apiErrorResponse The API error response to be mapped.
     * @return The mapped application error response.
     */

    override fun map(apiErrorResponse: ApiResponse.Failure.Error): ApplicationErrorResponse {
        return ApplicationErrorResponse(
            code = apiErrorResponse.statusCode.code,
            message = apiErrorResponse.message(),
        )
    }
}
package com.example.core.network.response

/**
 * Data class representing an application error response.
 * @param code The error code returned by the application.
 * @param message The error message returned by the application.
 */
data class ApplicationErrorResponse(
    val code: Int?,
    val message: String?,
)
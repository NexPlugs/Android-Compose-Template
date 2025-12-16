package com.example.core.network.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ErrorResponse(
    @SerialName("status")
    val status: Int,

    @SerialName("code")
    val code: String,

    @SerialName("message")
    val message: String
) {
    fun returnErrorMessage(): String {
        return message.takeIf { it.isNotBlank() } ?: "An unexpected error occurred. Please try again."
    }
}
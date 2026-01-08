package com.example.core.network.response.mapper.app

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * WARNING: This is mock object response
 */
@Serializable
data class AuthResponse(
    @SerialName("token") val token: String?,
    @SerialName("refreshToken") val refreshToken: String?,
)
package com.example.core.network.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * WARNING: This is mock object
 */
@Serializable
data class LoginRequest(
    @SerialName("username") val username: String,
    @SerialName("password") val password: String
)
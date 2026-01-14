package com.example.core.network.response.mapper.app

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable



/** * WARNING: This is mock object response */
@Serializable
data class UserResponse(
    @SerialName("id") val id: String,
    @SerialName("name") val name: String,
    @SerialName("email") val email: String
)
package com.example.core.network.service

import com.example.core.network.request.LoginRequest
import com.example.core.network.response.ApiResponse
import com.example.core.network.response.mapper.app.AuthResponse
import retrofit2.http.Body
import retrofit2.http.POST


/**
 * Service for authentication-related network operations.
 */
interface AuthService {
    companion object {
        private const val BRANCH = "auth"
    }

    @POST(value = "/$BRANCH/login")
    suspend fun login(@Body requestBody: LoginRequest, ): ApiResponse<AuthResponse>

}
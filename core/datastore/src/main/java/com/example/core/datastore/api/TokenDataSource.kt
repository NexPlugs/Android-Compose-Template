package com.example.core.datastore.api

import kotlinx.coroutines.flow.Flow


interface TokenDataSource {
    val accessToken: Flow<String>
    val refreshToken: Flow<String>
    suspend fun getAccessToken(): String
    suspend fun getRefreshToken(): String
    suspend fun saveTokens(accessToken: String, refreshToken: String)

    suspend fun setRefreshToken(refreshToken: String)
    suspend fun setAccessToken(accessToken: String)

    suspend fun clearTokens()
}


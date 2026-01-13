package com.goz247.data.api

import com.goz247.data.models.UserModel
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    suspend fun login(username: String, password: String): Result<Unit>


    suspend fun getUserInfo(): Flow<UserModel>
}
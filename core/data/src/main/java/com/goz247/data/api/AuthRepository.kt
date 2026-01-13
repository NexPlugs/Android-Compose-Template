package com.goz247.data.api

import com.goz247.data.models.UserModel
import kotlinx.coroutines.flow.Flow

/**
 * Repository interface for authentication-related operations
 */
interface AuthRepository {
    suspend fun login(username: String, password: String): Result<Unit>


    suspend fun getUserInfo(
        onStart: () -> Unit,
        onComplete: () -> Unit,
        onError: (String?) -> Unit
    ): Flow<UserModel>
}
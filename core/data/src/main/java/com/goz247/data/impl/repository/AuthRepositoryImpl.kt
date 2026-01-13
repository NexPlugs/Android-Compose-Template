package com.goz247.data.impl.repository

import com.example.core.common.utils.runSuspendCaching
import com.example.core.network.di.IoDispatcher
import com.example.core.network.request.LoginRequest
import com.example.core.network.service.AuthService
import com.goz247.data.api.AuthRepository
import com.goz247.data.models.UserModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * Implementation of AuthRepository
 */
class AuthRepositoryImpl @Inject constructor(
    private val authService: AuthService,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
): AuthRepository {
    companion object {
        private const val TAG = "AuthRepositoryImpl"
    }

    override suspend fun login(
        username: String,
        password: String
    ): Result<Unit> = runSuspendCaching {
        val response = authService.login(LoginRequest(username, password))
    }

    override suspend fun getUserInfo(): Flow<UserModel> = flow {

    }
}
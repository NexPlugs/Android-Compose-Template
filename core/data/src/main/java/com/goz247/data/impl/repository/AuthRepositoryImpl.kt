package com.goz247.data.impl.repository

import com.example.core.common.utils.runSuspendCaching
import com.example.core.network.di.IoDispatcher
import com.example.core.network.map
import com.example.core.network.message
import com.example.core.network.onError
import com.example.core.network.onFailure
import com.example.core.network.request.LoginRequest
import com.example.core.network.response.ApiResponse
import com.example.core.network.response.mapper.ErrorResponseMapper
import com.example.core.network.service.AuthService
import com.example.core.network.suspendOnSuccess
import com.goz247.data.api.AuthRepository
import com.goz247.data.models.UserModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
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

        if(response is ApiResponse.Success) {
            // Handle successful login, e.g., save tokens
            Result.success(Unit)
            return@runSuspendCaching
        }
        if(response is ApiResponse.Failure) {
            Result.failure<Unit>(Exception("Login failed: ${response.message()}"))
            return@runSuspendCaching
        }
    }

    override suspend fun getUserInfo(
        onStart: () -> Unit,
        onComplete: () -> Unit,
        onError: (String?) -> Unit
    ): Flow<UserModel> = flow {
        val response = authService.userInfo()

        response.suspendOnSuccess {
            emit(this.da)
        }.onError {
            map(ErrorResponseMapper()) { onError(message) }
        }
    }.onStart { onStart() }.onCompletion { onComplete() }.flowOn(ioDispatcher)
}
package com.goz247.data.impl.repository

import com.example.core.common.utils.runSuspendCaching
import com.example.core.model.UserModel
import com.example.core.network.di.IoDispatcher
import com.example.core.network.map
import com.example.core.network.mapResult
import com.example.core.network.message
import com.example.core.network.onError
import com.example.core.network.onException
import com.example.core.network.request.LoginRequest
import com.example.core.network.response.mapper.ErrorResponseMapper
import com.example.core.network.response.mapper.app.AuthResponse
import com.example.core.network.service.AuthService
import com.example.core.network.suspendOnSuccess
import com.goz247.data.api.AuthRepository
import com.goz247.data.impl.mapper.UserInfoMapper
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

    override suspend fun login(
        username: String,
        password: String
    ): Result<Unit> = runSuspendCaching {
        val response = authService.login(LoginRequest(username, password))
        response.mapResult<AuthResponse, Unit>(
            onResult = {
                //TODO: Save auth token or any required data from AuthResponse
            },
            onError = { throw Exception(message()) },
            onException = { throw Exception(message()) }
        )
    }


    override suspend fun getUserInfo(): Flow<UserModel> = flow {
        val response = authService.userInfo()

        response.suspendOnSuccess(UserInfoMapper()) {
            emit(this)
        }.onError {
            map(ErrorResponseMapper()) { throw Exception(message) }
        }.onException {
            map(ErrorResponseMapper()) { throw Exception(message) }
        }
    }
        .flowOn(ioDispatcher)
}
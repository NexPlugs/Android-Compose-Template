package com.example.auth

import androidx.lifecycle.viewModelScope
import com.example.core.viewmodel.BaseViewModel
import com.example.core.viewmodel.ViewModelStateFlow
import com.goz247.data.api.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    val authRepository: AuthRepository
): BaseViewModel() {
    internal val uiState: ViewModelStateFlow<AuthUiState> = viewModelStateFlow(AuthUiState.Loading)

    fun handleLogin(
        username: String,
        password: String
    ) {
        viewModelScope.launch {
            uiState.emit(key, AuthUiState.Loading)

            authRepository.login(username, password).onSuccess {
                uiState.emit(key, AuthUiState.Success)
            }.onFailure { error ->
                uiState.emit(key, AuthUiState.Error(message = error.message))
            }
        }
    }

}


sealed interface AuthUiState {
    object Loading: AuthUiState
    data object Success: AuthUiState
    data class Error(val message: String?): AuthUiState
}

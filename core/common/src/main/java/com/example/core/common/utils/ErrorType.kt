package com.example.core.common.utils

import androidx.compose.runtime.Immutable


@Immutable
sealed interface ErrorType {
    data class MessageError(val message: String) : ErrorType
    data class ResourceError(val resId: Int) : ErrorType
}
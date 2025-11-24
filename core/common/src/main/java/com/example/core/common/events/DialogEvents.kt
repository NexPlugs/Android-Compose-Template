package com.example.core.common.events

import com.example.core.common.constants.ErrorScope


fun postErrorDialog(
    errorScope: ErrorScope,
    exception: Throwable,
    confirmLabel: String,
    cancelLabel: String? = null,
    onConfirm: () -> Unit = {},
) {

    val (title, message) = when (errorScope) {
        ErrorScope.GLOBAL -> "Global Error" to "An unexpected error occurred: ${exception.message}"
        ErrorScope.LOGIN -> "Login Error" to "Failed to login: ${exception.message}"
        ErrorScope.AUTH_SESSION_EXPIRED -> "Session Expired" to "Your session has expired. Please log in again."
    }
    val dialogSpec = DialogSpec(
        title = title,
        message = message,
        positiveButtonText = confirmLabel,
        negativeButtonText = cancelLabel,
        onPositiveClick = onConfirm,
        onNegativeClick = null
    )
}
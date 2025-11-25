package com.example.core.common.events

import com.example.core.common.constants.ErrorScope

/**
 * Posts an error dialog event based on the provided error scope and exception.
 */
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

    EventHelper.sendEvent(AppEvent.ShowDialog(dialogSpec))
}

/**
 * Posts a general information dialog event.
 */
fun postInfoDialog(
    title: String,
    message: String,
    confirmLabel: String,
    cancelLabel: String? = null,
    onConfirm: () -> Unit = {},
) {
    val dialogSpec = DialogSpec(
        title = title,
        message = message,
        positiveButtonText = confirmLabel,
        negativeButtonText = cancelLabel,
        onPositiveClick = onConfirm,
        onNegativeClick = null
    )

    EventHelper.sendEvent(AppEvent.ShowDialog(dialogSpec))
}
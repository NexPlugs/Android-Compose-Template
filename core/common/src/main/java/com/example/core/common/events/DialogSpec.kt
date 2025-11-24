package com.example.core.common.events

data class DialogSpec(
    val title: String,
    val message: String,
    val positiveButtonText: String,
    val negativeButtonText: String? = null,
    val onPositiveClick: () -> Unit,
    val onNegativeClick: (() -> Unit)? = null,
)

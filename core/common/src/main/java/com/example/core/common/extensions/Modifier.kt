package com.example.core.common.extensions

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.ui.Modifier


/**
 * A [Modifier] extension that makes a composable clickable without showing any ripple effect.
 */
@SuppressLint("SuspiciousModifierThen")
fun Modifier.noRippleClickable(
    onClick: () -> Unit
): Modifier = this.then(
    clickable(
        indication = null,
        interactionSource = androidx.compose.foundation.interaction.MutableInteractionSource(),
        onClick = onClick
    )
)
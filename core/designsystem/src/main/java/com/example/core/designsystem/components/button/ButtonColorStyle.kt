package com.example.core.designsystem.components.button

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable


/**
 * Enum class that defines different color styles for buttons.
 * Each style provides methods to get the appropriate colors based on the button's state.
 */
enum class ButtonColorStyle {
    PRIMARY,
    SECONDARY,
    TERTIARY;

    @Composable
    fun containerColor(isPressed: Boolean) = when(this) {
        PRIMARY -> if(isPressed) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.primaryContainer
        SECONDARY -> if(isPressed) MaterialTheme.colorScheme.secondary else MaterialTheme.colorScheme.secondaryContainer
        TERTIARY -> if(isPressed) MaterialTheme.colorScheme.tertiary else MaterialTheme.colorScheme.tertiaryContainer
    }

    @Composable
    fun contentColor() = when(this) {
        PRIMARY -> MaterialTheme.colorScheme.onPrimaryContainer
        SECONDARY -> MaterialTheme.colorScheme.onSecondaryContainer
        TERTIARY -> MaterialTheme.colorScheme.onTertiaryContainer
    }


    @Composable
    fun disableContainerColor() = when(this) {
        PRIMARY -> MaterialTheme.colorScheme.onSurface.copy(alpha = 0.12f)
        SECONDARY -> MaterialTheme.colorScheme.onSurface.copy(alpha = 0.12f)
        TERTIARY -> MaterialTheme.colorScheme.onSurface.copy(alpha = 0.12f)
    }


    @Composable
    fun borderStroke() = when(this) {
        PRIMARY -> null
        SECONDARY -> null
        TERTIARY -> null
    }
}
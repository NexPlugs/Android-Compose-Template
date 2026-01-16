package com.example.core.designsystem.components.button

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * Data class that holds style information for buttons.
 */
data class ButtonStyle(
    val paddingValues: PaddingValues,
    val radius: Dp = 0.dp,
    val textStyle: TextStyle,
    val iconSpacing: Dp = 0.dp,
    val iconSize: Dp = 24.dp
)

val largeButtonStyle: ButtonStyle
    @Composable get() = ButtonStyle(
        paddingValues = PaddingValues(
            start = 16.dp,
            top = 12.dp,
            end = 16.dp,
            bottom = 12.dp
        ),
        radius = 8.dp,
        textStyle = MaterialTheme.typography.bodyLarge,
        iconSpacing = 8.dp,
    )
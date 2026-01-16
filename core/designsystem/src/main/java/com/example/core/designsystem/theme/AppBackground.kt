package com.example.core.designsystem.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp


@Immutable
data class AppBackground(
    val color: Color = Color.Unspecified,
    val tonalElevation: Dp = Dp.Unspecified
) {
    companion object {

        @Composable
        fun defaultBackground(darkTheme: Boolean): AppBackground {
            return if(darkTheme) {
                AppBackground(
                    color = Color(0xFF121212),
                    tonalElevation = 0.dp
                )
            } else {
                AppBackground(
                    color = Color(0xFFFFFFFF),
                    tonalElevation = 0.dp
                )
            }
        }
    }
}


val LocalBackgroundTheme: ProvidableCompositionLocal<AppBackground> =
    staticCompositionLocalOf { AppBackground() }
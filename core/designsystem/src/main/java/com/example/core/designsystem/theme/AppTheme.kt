package com.example.core.designsystem.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTagsAsResourceId


/**
 * Composition local provider for [AppColors]
 */
private val LocalColors = compositionLocalOf<AppColors> {
    error("No colors provided")
}

/**
 * Accessor to get current [AppColors]
 */
@Composable
fun AppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    colors: AppColors = if(darkTheme)
        AppColors.defaultDarkMode()
    else
        AppColors.defaultLightMode(),
    background: AppBackground = AppBackground.defaultBackground(darkTheme),
    content: @Composable () -> Unit
) {
    CompositionLocalProvider(
        LocalColors provides colors,
        LocalBackgroundTheme provides background,
    ) {
        Box(
            modifier = Modifier
                .background(background.color)
                .semantics { testTagsAsResourceId = true },
        ) {
            content()
        }
    }
}


/**
 * Object to access current theme colors and background
 *
 * val colors = ApplicationTheme.colors
 * val background = ApplicationTheme.currentBackground
 */
object ApplicationTheme {

    /**
     * Current background theme
     */
    val currentBackground: AppBackground
        @Composable
        get() = LocalBackgroundTheme.current


    /**
     * Current colors theme
     */
    val colors: AppColors
        @Composable
        get() = LocalColors.current
}
package com.example.core.navigation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf

/**
 * CompositionLocal to provide AppNavigator throughout the Compose hierarchy
 */
val LocalComposeNavigator: ProvidableCompositionLocal<AppNavigator> =
    staticCompositionLocalOf {
        throw IllegalStateException("No Navigator provided")
    }


/**
 * Current AppNavigator from the CompositionLocal
 */
val currentComposeNavigator: AppNavigator
    @Composable
    @ReadOnlyComposable
    get() = LocalComposeNavigator.current
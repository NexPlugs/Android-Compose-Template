package com.example.androidcomposetemplate.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import com.example.auth.AuthScreen
import com.example.core.navigation.AppNavigator
import com.example.core.navigation.ApplicationScreen
import com.example.core.navigation.LocalComposeNavigator

@Composable
fun AppNavHost() {
    val backStack = rememberNavBackStack(ApplicationScreen.Auth)
    val navigator = remember(backStack) { AppNavigator(backStack) }


    CompositionLocalProvider(
        LocalComposeNavigator provides navigator
    ) {

        NavDisplay(
            backStack = backStack,
            onBack = { navigator.navigationUp() },
            entryDecorators = listOf(rememberSaveableStateHolderNavEntryDecorator()), // This enables state saving and restoration for each NavEntry
            entryProvider = entryProvider<NavKey> {
                entry<ApplicationScreen.Auth> { AuthScreen() }
            }
        )

    }
}
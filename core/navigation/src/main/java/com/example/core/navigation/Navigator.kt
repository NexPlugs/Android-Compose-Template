package com.example.core.navigation

import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey

/**
 * Navigator interface to handle navigation between screens
 * Implementations of this interface should provide the logic for navigating to different screens,
 */
interface Navigator {
    fun navigateTo(screen: ApplicationScreen)

    fun back()

    fun navigationUp(): Boolean
}


/**
 * AppNavigator implementation using NavBackStack to manage navigation
 * @param backStack The NavBackStack to manage the navigation stack
 */
class AppNavigator(
    private val backStack: NavBackStack<NavKey>
): Navigator {
    override fun navigateTo(screen: ApplicationScreen) {
        backStack.add(screen)
    }

    override fun back() {
        backStack.removeLastOrNull()
    }

    override fun navigationUp(): Boolean {
        return if (backStack.size > 1) {
            backStack.removeLastOrNull() != null
        } else {
            false
        }
    }
}
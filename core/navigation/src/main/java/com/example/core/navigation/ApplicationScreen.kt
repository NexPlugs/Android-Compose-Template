package com.example.core.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

/**
 * Application screens used in Navigation
 * Use Serializable for NavKey implementation, handle encoding/decoding automatically
 */
sealed class ApplicationScreen: NavKey {
    @Serializable
    data object Home: ApplicationScreen()

    @Serializable
    data class Detail(val id: Int): ApplicationScreen()


    @Serializable
    data object Auth: ApplicationScreen()

}



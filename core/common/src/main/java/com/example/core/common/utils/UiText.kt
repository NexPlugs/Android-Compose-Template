package com.example.core.common.utils

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource

/**
 * A sealed class to represent text that can be either a direct string or a string resource.
 */
sealed class UiText {
    /** A direct string value. */
    data class DirectString(val value: String) : UiText()

    /** A string resource with optional formatting arguments. */
    class StringResource(
        val resId: Int,
        vararg val args: Any
    ): UiText()

    /** Converts the UiText to a String using Composes stringResource. */
    @Composable
    fun asString(): String {
        return when (this) {
            is DirectString -> value
            is StringResource -> {
                if (args.isNotEmpty()) {
                    stringResource(id = resId, *args)
                } else {
                    stringResource(id = resId)
                }
            }
        }
    }

    /** Converts the UiText to a String using the provided Context. */
    fun asString(context: Context) : String {
        return when (this) {
            is DirectString -> value
            is StringResource -> {
                if (args.isNotEmpty()) {
                    context.getString(resId, *args)
                } else {
                    context.getString(resId)
                }
            }
        }
    }
}
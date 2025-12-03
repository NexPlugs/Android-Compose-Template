package com.example.core.common.extensions

import android.annotation.SuppressLint
import android.content.Context
import kotlin.String

/**
 * Extension function to get a string resource by its name.
 *
 * @param resourceName The name of the string resource.
 * @return The string value if found, otherwise an empty string.
 */
@SuppressLint("DiscouragedApi")
fun Context.getStringResourceByName(resourceName: String): String {
    val packageName = this.packageName
    val resId = this.resources.getIdentifier(resourceName, "string", packageName)
    return if (resId != 0) {
        this.getString(resId)
    } else {
        ""
    }
}
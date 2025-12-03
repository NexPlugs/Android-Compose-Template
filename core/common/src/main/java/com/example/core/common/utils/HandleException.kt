package com.example.core.common.utils


import com.example.core.common.constants.ErrorScope
import com.example.core.common.events.postErrorDialog
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.Json
import retrofit2.HttpException
import java.io.IOException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException


fun handleException(
    exception: Throwable,
    onError: (String) -> Unit,
    onLoginRequired: () -> Unit = {}
){
    when {
        exception is HttpException && exception.code() == 401 -> {
            postErrorDialog(
                errorScope = ErrorScope.AUTH_SESSION_EXPIRED,
                exception = exception,
                confirmLabel = "OK",
                onConfirm = {
                    onLoginRequired()
                }
            )
        }
        exception is HttpException -> {
            val errorMessage = exception.parseErrorMessage()
            if (!errorMessage.isNullOrBlank()) {
                onError(errorMessage)
            } else {
                onError("HTTP Error: ${exception.code()} ${exception.message()}")
            }
        }
        exception.isNetworkError() -> {
            onError("Network error occurred. Please check your internet connection.")
        }
        else -> {
            onError("An unexpected error occurred: ${exception.message}")
        }
    }
}

private fun HttpException.parseErrorMessage(): String? {
    return try {
        val errorBody = response()?.errorBody()?.string()
        if(errorBody.isNullOrBlank()) return null
        Json.decodeFromString(errorBody)
    } catch (e: SerializationException) {
        null
    } catch (e: IOException) {
        null
    } catch (e: Exception) {
        null
    }

}


fun Throwable.isNetworkError(): Boolean {
    return this is UnknownHostException ||
            this is SocketTimeoutException ||
            this is ConnectException ||
            this is IOException
}
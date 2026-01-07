package com.example.core.network.response

import com.example.core.network.NetworkInitializer
import com.example.core.network.operator
import com.example.core.network.operators.ApiResponseOperator
import com.example.core.network.operators.ApiResponseSuspendOperator
import com.example.core.network.suspendOperator
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.launch

/**
 * A generic sealed class to represent API responses.
 *
 * @param T The type of data expected in a successful response.
 */
sealed interface ApiResponse<out T> {

    /** Represents a successful API response containing data of type [T]. */
    data class Success<T>(val data: T, val tag: Any? = null) : ApiResponse<T>


    /** Represents a failed API response. */
    sealed interface Failure<T>: ApiResponse<T> {


        /**
         * Represents an error response with an optional payload.
         * @param payload The error payload, can be of any type.
         */
        open class Error(val payload: Any?) : Failure<Nothing> {

            override fun toString(): String = payload.toString()

            override fun hashCode(): Int {
                var result = 17
                result = 31 * result + (payload?.hashCode() ?: 0)
                return result
            }

            override fun equals(other: Any?): Boolean = other is Error &&
                    other.payload == this.payload
        }


        /**
         * Represents an exception that occurred during the API call.
         * @param throwable The exception thrown.
         */
        open class Exception(val throwable: Throwable) : Failure<Nothing> {

            override fun toString(): String = throwable.message.orEmpty()

            override fun hashCode(): Int {
                var result = 17
                result = 31 * result + throwable.hashCode()
                return result
            }

            override fun equals(other: Any?): Boolean = other is Exception &&
                    other.throwable == this.throwable
        }

    }

    companion object {
        /**
         * Creates an [ApiResponse.Failure.Error] instance with the given payload and applies global operators.
         *
         *  @param throwable The error payload.
         * @return An instance of [ApiResponse.Failure.Error].
         */
        fun exception(throwable: Throwable): Failure.Exception =
            Failure.Exception(throwable).operate()  as Failure.Exception


        /**
         * Executes the given [block] and returns an [ApiResponse].
         * If the block executes successfully, a [Success] response is returned.
         * If an exception occurs, an [ApiResponse.Failure.Exception] response is returned.
         *
         * @param block The block of code to execute.
         * @param tag An optional tag to associate with the response.
         * @return An instance of [ApiResponse].
         */
        inline fun<T> of(block: () -> T, tag: Any? = null): ApiResponse<T> = try {
            val result = block()
            Success(tag = tag,  data = result)
        } catch (e: Exception) {
            exception(e)
        }.operate()

        /**
         * Executes the given suspend [block] and returns an [ApiResponse].
         * If the block executes successfully, a [Success] response is returned.
         * If an exception occurs, an [ApiResponse.Failure.Exception] response is returned.
         * @param block The suspend block of code to execute.
         * @param tag An optional tag to associate with the response.
         * @return An instance of [ApiResponse].
         */
        suspend inline fun<T> suspendOf(block: suspend () -> T, tag: Any? = null): ApiResponse<T> = try {
            val result = block()
            Success(tag = tag, data = result)
        } catch (e: CancellationException) {
            throw e
        } catch (e: Exception) {
            exception(e)
        }.operate()

        /**
         * Creates an [ApiResponse.Failure.Error] instance with the given payload and applies global operators.
         */
        @Suppress("UNCHECKED_CAST")
        fun<T> ApiResponse<T>.operate(): ApiResponse<T> = apply {
            val globalOperators = NetworkInitializer.networkOperators
            globalOperators.forEach { op ->
                if(op is ApiResponseOperator<*>) {
                    operator(op as ApiResponseOperator<T>)
                } else if(op is ApiResponseSuspendOperator<*>) {
                    val scope = NetworkInitializer.networkScope
                    scope.launch {
                        suspendOperator(op as ApiResponseSuspendOperator<T>)
                    }
                }
            }
        }
    }
}
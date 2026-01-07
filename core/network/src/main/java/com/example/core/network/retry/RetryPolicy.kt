package com.example.core.network.retry


/**
 * An interface defining a retry policy for network operations.
 * Implementations of this interface can specify the conditions under which
 */
interface RetryPolicy {

    /**
     * Determines whether a retry should be attempted based on the current attempt count
     * and the exception encountered.
     *
     * @param attempt The current attempt count (nullable).
     * @param exception The exception encountered during the operation (nullable).
     * @return True if a retry should be attempted, false otherwise.
     */
    fun shouldRetry(attempt: Int? = null, exception: String? = null): Boolean


    /**
     * Specified the maximum number of retry attempts allowed.
     *
     * @param attempt The current attempt count (nullable).
     * @param exception The exception encountered during the operation (nullable).
     */
    fun retryTimeOut(attempt: Int?, exception: String?): Int
}
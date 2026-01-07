package com.example.core.network.retry

import com.example.core.network.SuspendFunction
import com.example.core.network.response.ApiResponse
import kotlinx.coroutines.delay


/**
 * Retries the given [block] according to the provided [retryPolicy]
 * @param T The type of the ApiResponse
 * @param retryPolicy The policy that defines the retry behavior
 * @param block The suspend function to be retried
 *
 * @return The final ApiResponse after applying the retry logic
 */
@SuspendFunction
suspend fun<T> runRetry(
    retryPolicy: RetryPolicy,
    block: suspend (attempt: Int, exception: String?) -> ApiResponse<T>,
): ApiResponse<T> {
    var attempt = 1
    var exception: String? = null
    var apiResponse: ApiResponse<T>
    while(true) {
        apiResponse = block(attempt, exception)
        when(apiResponse) {
            is ApiResponse.Success -> return apiResponse
            is ApiResponse.Failure -> {
                exception = apiResponse.toString()
                val shouldRetry =  retryPolicy.shouldRetry(attempt, exception)
                val retryTimeOut = retryPolicy.retryTimeOut(attempt, exception)
                if(shouldRetry) {
                    delay(retryTimeOut.toLong())
                    attempt++
                } else {
                    break
                }
            }

        }
    }
    return apiResponse
}
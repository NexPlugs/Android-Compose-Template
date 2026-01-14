@file:Suppress("WRONG_INVOCATION_KIND")

package com.example.core.common.utils

import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract
import kotlin.coroutines.cancellation.CancellationException


/**
 * Runs a suspending block of code and caches its result in a [Result] object.
 * This function captures any exceptions thrown during the execution of the block
 * and wraps them in a [Result.failure]. If the block executes successfully,
 */
@OptIn(ExperimentalContracts::class)
@Suppress("WRONG_INVOCATION_KIND")
inline fun<T> runSuspendCaching(block: () -> T): Result<T> {
    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }

    return try {
        Result.success(block())
    } catch (reThrow: CancellationException) {
        throw reThrow
    } catch (exception: Exception) {
        Result.failure(exception)
    }

}
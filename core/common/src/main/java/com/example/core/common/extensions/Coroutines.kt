package com.example.core.common.extensions

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock


fun CoroutineScope.launchWithMutex( mutex: Mutex, block: suspend () -> Unit) = launch {
    mutex.withLock { block() }
}
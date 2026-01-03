package com.example.core.viewmodel

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.ExperimentalForInheritanceCoroutinesApi
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow


@OptIn(ExperimentalForInheritanceCoroutinesApi::class)
class ViewModelStateFlow<T>(private val key: ViewModelKey, value: T) : MutableStateFlow<T> {
    override var value: T
        get() = TODO("Not yet implemented")
        set(value) {}

    override fun compareAndSet(expect: T, update: T): Boolean {
        TODO("Not yet implemented")
    }

    override val replayCache: List<T>
        get() = TODO("Not yet implemented")

    override suspend fun collect(collector: FlowCollector<T>): Nothing {
        TODO("Not yet implemented")
    }

    override val subscriptionCount: StateFlow<Int>
        get() = TODO("Not yet implemented")

    override suspend fun emit(value: T) {
        TODO("Not yet implemented")
    }

    override fun tryEmit(value: T): Boolean {
        TODO("Not yet implemented")
    }

    @ExperimentalCoroutinesApi
    override fun resetReplayCache() {
        TODO("Not yet implemented")
    }

}
package com.example.core.viewmodel

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.ExperimentalForInheritanceCoroutinesApi
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow


/**
 * * A [MutableStateFlow] that holds a value associated with a [ViewModelKey].
 * * This is used to scope state flows to specific ViewModel instances.
 * * @param T The type of the value held by the state flow.
 * * @property key The [ViewModelKey] associated with this state flow.
 * * @property value The initial value of the state flow.
 */
@OptIn(ExperimentalForInheritanceCoroutinesApi::class)
class ViewModelStateFlow<T>(private val key: ViewModelKey, value: T) : MutableStateFlow<T> {

    /** * The underlying [MutableStateFlow] that holds a map of [ViewModelKey] to value. */
    private val mutableStateFLow: MutableStateFlow<Map<ViewModelKey, T>> = MutableStateFlow(mapOf(key to value))

    override val subscriptionCount: StateFlow<Int>
        get() = mutableStateFLow.subscriptionCount

    suspend fun emit(key: ViewModelKey, value: T) {
        if(this.key != key) {
            throw IllegalArgumentException("Key mismatch: expected $this.key but got $key")
        }
        mutableStateFLow.emit(mapOf(key to value))
    }

    /** * Emits a value associated with the given [key]. */
    override suspend fun emit(value: T) =
        throw IllegalAccessError("Use `emitValue` function instead of this")

    fun tryEmit(key: ViewModelKey, value: T): Boolean {
        if(this.key != key) {
            throw IllegalArgumentException("Key mismatch: expected $this.key but got $key")
        }
        return mutableStateFLow.tryEmit(mapOf(key to value))
    }

    /** * Tries to emit a value associated with the given [key]. */
    override fun tryEmit(value: T): Boolean {
        throw IllegalAccessError("Use `tryEmitValue` function instead of this")
    }

    override var value: T
        get() = mutableStateFLow.value.getValue(key)
        set(value) {
            mutableStateFLow.value = mapOf(key to value)
        }

    override fun compareAndSet(expect: T, update: T): Boolean =
        mutableStateFLow.compareAndSet(mapOf(key to expect), mapOf(key to update))

    override val replayCache: List<T>
        get() = mutableStateFLow.replayCache.map { value }

    override suspend fun collect(collector: FlowCollector<T>): Nothing {
        return mutableStateFLow.collect {
            collector.emit(it.getValue(key))
        }
    }

    @ExperimentalCoroutinesApi
    override fun resetReplayCache() = mutableStateFLow.resetReplayCache()

}
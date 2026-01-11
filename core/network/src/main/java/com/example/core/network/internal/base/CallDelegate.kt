package com.example.core.network.internal.base

import okhttp3.Request
import okio.Timeout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * A base class for Call delegates.
 */
internal abstract class CallDelegate<In, Out>(protected val proxy: Call<In>) : Call<Out> {

    /** Adapts the given call to another call type. */
    override fun execute(): Response<Out> = executeImpl()
    override fun enqueue(callback: Callback<Out>) = enqueueImpl(callback)
    override fun isExecuted(): Boolean = proxy.isExecuted
    override fun cancel() = proxy.cancel()
    override fun isCanceled(): Boolean = proxy.isCanceled
    override fun clone(): Call<Out> = cloneImpl()
    override fun request(): Request = proxy.request()
    override fun timeout(): Timeout = proxy.timeout()


    /** Adapts the given call to another call type. */
    abstract fun enqueueImpl(callback: Callback<Out>)

    /** Cancels the call. */
    abstract fun executeImpl(): Response<Out>

    /** Clones the call. */
    abstract fun cloneImpl(): Call<Out>
}
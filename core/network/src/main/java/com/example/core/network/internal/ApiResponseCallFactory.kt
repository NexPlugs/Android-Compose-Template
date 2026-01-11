package com.example.core.network.internal

import com.example.core.network.response.ApiResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Retrofit
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

/**
 * A CallAdapter.Factory that creates CallAdapters for ApiResponse wrapped in Call or Deferred.
 * @param coroutineScope The CoroutineScope in which to execute the calls.
 */
class ApiResponseCallFactory private constructor(
    private val coroutineScope: CoroutineScope
): CallAdapter.Factory() {
    override fun get(
        returnType: Type,
        annotations: Array<out Annotation?>,
        retrofit: Retrofit
    ): CallAdapter<*, *>? {
        when(getRawType(returnType)) {
            Call::class.java -> {
                val callType = getParameterUpperBound(0, returnType as ParameterizedType)
                val rawType = getRawType(callType)
                if(rawType != ApiResponse::class.java) {
                    return null
                }
                val resultType = getParameterUpperBound(0, callType as ParameterizedType)
                return ApiResponseCallAdapter(
                    resultType,
                    coroutineScope
                )

            }
            Deferred::class.java -> {
                val deferredType = getParameterUpperBound(0, returnType as ParameterizedType)
                val rawType = getRawType(deferredType)
                if(rawType != ApiResponse::class.java) {
                    return null
                }
                val resultType = getParameterUpperBound(0, deferredType as ParameterizedType)
                return ApiResponseDeferredCallAdapter<Any>(
                    resultType,
                    coroutineScope
                )
            }
            else -> return null
        }
        return null
    }

    companion object {
        /** Factory method to create an instance of ApiResponseCallFactory. */
        @JvmStatic
        fun create(
            coroutineScope: CoroutineScope
        ): ApiResponseCallFactory = ApiResponseCallFactory(coroutineScope)
    }
}
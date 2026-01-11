package com.example.core.network

import com.example.core.network.operators.NetworkOperator
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob


/**
 * Object to initialize network configurations.
 */
object NetworkInitializer {

    /** Tag used for logging network operations */
    var loggerTag: String = "NetworkLogger"

    /** Range of HTTP success status codes */
    @JvmStatic
    var successCodeRange: IntRange = 200..299

    /** List of network operators. Use for handle Global data tracking */
    @JvmStatic
    var networkOperators: MutableList<NetworkOperator> = mutableListOf()

    /** Network timeout duration in milliseconds */
    @JvmStatic
    var networkTimeOut: Long? = null

    /** Coroutine scope for network operations */
    @JvmStatic
    var networkScope: CoroutineScope = CoroutineScope(Dispatchers.IO + SupervisorJob())

}
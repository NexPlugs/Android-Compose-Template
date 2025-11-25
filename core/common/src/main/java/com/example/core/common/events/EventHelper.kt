package com.example.core.common.events

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow


/**
 * Helper object to send and receive application-wide events using Kotlin Coroutines Channels and Flows.
 * This allows different parts of the application to communicate asynchronously.
 * Usage:
 * - To send an event: EventHelper.sendEvent(AppEvent.ShowDialog(spec))
 * - To listen for events: EventHelper.eventFlow.collect { event -> ... }
 */
object EventHelper {
    private val _channel = Channel<AppEvent>(Channel.BUFFERED)
    val eventFlow = _channel.receiveAsFlow()

    fun sendEvent(event: AppEvent) {
        _channel.trySend(event)
    }
}


/**
 * Sealed class representing different types of application events.
 * Extend this class to add more event types as needed.
 */
sealed class AppEvent {
    data class ShowDialog(val spec: DialogSpec) : AppEvent()
}
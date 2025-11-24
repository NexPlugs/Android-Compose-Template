package com.example.core.common.events


object EventHelper { }


sealed class AppEvent {
    data class ShowDialog(val spec: DialogSpec) : AppEvent()
}
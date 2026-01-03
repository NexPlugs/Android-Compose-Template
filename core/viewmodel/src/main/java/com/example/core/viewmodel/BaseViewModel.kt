package com.example.core.viewmodel

import androidx.lifecycle.ViewModel

/** * A base class for ViewModels that provides a unique key and helper functions. */
abstract class BaseViewModel: ViewModel() {

    /** * A unique key for this ViewModel instance. */
    protected val key: ViewModelKey = ViewModelKey(this::class.java.name)

    /** * Creates a [ViewModelStateFlow] with the given [initialValue] associated with this ViewModel's key. */
    protected fun<T> BaseViewModel.viewModelStateFlow(initialValue: T): ViewModelStateFlow<T> {
        return ViewModelStateFlow(key, initialValue)
    }
}
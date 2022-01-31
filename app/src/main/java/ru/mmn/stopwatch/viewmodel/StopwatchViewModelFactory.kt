package ru.mmn.stopwatch.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.mmn.stopwatch.state.StopwatchStateHolder

class StopwatchViewModelFactory(private val stopwatchStateHolder: StopwatchStateHolder): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(StopwatchViewModel::class.java)){
            return StopwatchViewModel(stopwatchStateHolder = stopwatchStateHolder) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }
}

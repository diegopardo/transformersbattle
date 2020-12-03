package com.diegopardo.transformersbattle.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.diegopardo.transformersbattle.api.service.AllSparkService
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel  @Inject constructor(
    private val allSparkService: AllSparkService
) : ViewModel() {

    fun auth() {
        viewModelScope.launch {
            val getAllSparkCall = allSparkService.getAllSpark()
            getAllSparkCall.let {
                it.hashCode()
            }
        }
    }
}
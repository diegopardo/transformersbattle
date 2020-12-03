package com.diegopardo.transformersbattle.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.diegopardo.transformersbattle.repository.AllSparkRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class TransformersViewModel  @Inject constructor(
    private val allSparkRepository: AllSparkRepository
) : ViewModel() {

    fun auth() {
        viewModelScope.launch {
            val token = allSparkRepository.getAllSpark()
            if (!token.isNullOrEmpty()) {
                // TODO: Store in shared prefs
            } else {
                // TODO: Inform user about error
            }
        }
    }
}
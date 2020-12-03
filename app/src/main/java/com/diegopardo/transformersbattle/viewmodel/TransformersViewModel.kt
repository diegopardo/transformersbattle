package com.diegopardo.transformersbattle.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.diegopardo.transformersbattle.repository.TransformersRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class TransformersViewModel  @Inject constructor(
    private val transformersRepository: TransformersRepository
) : ViewModel() {

    fun getTransformers() {
        viewModelScope.launch {
            val transformers = transformersRepository.getTransformers()
            if (!transformers.isNullOrEmpty()) {
                // TODO: put in live data
            } else {
                // TODO: Inform user about error
            }
        }
    }
}
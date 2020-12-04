package com.diegopardo.transformersbattle.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.diegopardo.transformersbattle.model.pojo.Transformer
import com.diegopardo.transformersbattle.repository.TransformersRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class TransformersViewModel  @Inject constructor(
    private val transformersRepository: TransformersRepository
) : ViewModel() {

    var transformerList: MutableLiveData<List<Transformer>> = MutableLiveData()

    fun getTransformers() {
        viewModelScope.launch {
            val transformers = transformersRepository.getTransformers()
            if (!transformers.isNullOrEmpty()) {
                transformerList.postValue(transformers)
            } else {
                // TODO: Inform user about error
            }
        }
    }
}
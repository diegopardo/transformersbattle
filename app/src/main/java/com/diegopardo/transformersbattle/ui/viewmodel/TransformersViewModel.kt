package com.diegopardo.transformersbattle.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.diegopardo.transformersbattle.model.dto.TransformerDTO
import com.diegopardo.transformersbattle.model.pojo.Transformer
import com.diegopardo.transformersbattle.repository.TransformersRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class TransformersViewModel  @Inject constructor(
    private val transformersRepository: TransformersRepository
) : ViewModel() {

    var transformerList: MutableLiveData<ArrayList<Transformer>> = MutableLiveData()
    var newTransformer: MutableLiveData<Transformer> = MutableLiveData()
    var updatedTransformer: MutableLiveData<Transformer> = MutableLiveData()
    var deletedTransformer: MutableLiveData<Transformer> = MutableLiveData()

    fun getTransformers() {
        viewModelScope.launch {
            val transformers = transformersRepository.getTransformers()
            if (!transformers.isNullOrEmpty()) {
                transformerList.postValue(transformers as ArrayList<Transformer>)
            } else {
                // TODO: Inform user about error
            }
        }
    }

    fun createTransformer(transformer: TransformerDTO) {
        viewModelScope.launch {
            val transformer = transformersRepository.createTransformer(transformer)
            transformer?.let {
                transformerList.value?.add(transformer)
                newTransformer.postValue(transformer)
            } ?: run {
                // TODO: Inform user about error
            }
        }
    }

    fun updateTransformer(transformer: TransformerDTO) {
        viewModelScope.launch {
            val transformer = transformersRepository.updateTransformer(transformer)
            transformer?.let {
                transformerList.value?.apply {
                    set(indexOf(it), it)
                }
                updatedTransformer.postValue(transformer)
            } ?: run {
                // TODO: Inform user about error
            }
        }
    }

    fun deleteTransformer(transformerId: String) {
        viewModelScope.launch {
            val deleted = transformersRepository.deleteTransformer(transformerId)
            if (deleted) {
                val transformer = Transformer.newEmptyInstanceWithId(transformerId)
                transformerList.value?.remove(transformer)
                deletedTransformer.postValue(transformer)
            } else {
                // TODO: Inform user about error
            }
        }
    }
}
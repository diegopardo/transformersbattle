package com.diegopardo.transformersbattle.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.diegopardo.transformersbattle.repository.AllSparkRepository
import com.diegopardo.transformersbattle.utils.ACCESS_TOKEN
import com.diegopardo.transformersbattle.utils.PreferencesHelper
import kotlinx.coroutines.launch
import javax.inject.Inject

//class AllSparkViewModel  @Inject constructor(
//    private val allSparkRepository: AllSparkRepository,
//    private val preferencesHelper: PreferencesHelper,
//) : ViewModel() {
//
//    fun getAllSpark() {
//        viewModelScope.launch {
//            val token = allSparkRepository.getAllSpark()
//            if (!token.isNullOrEmpty()) {
//                preferencesHelper.save(ACCESS_TOKEN, token)
//            } else {
//                preferencesHelper.clearSharedPrefs()
//                // TODO: Inform user about error
//            }
//        }
//    }
//}
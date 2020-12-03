package com.diegopardo.transformersbattle.repository

import com.diegopardo.transformersbattle.api.service.AllSparkService
import java.net.HttpURLConnection
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AllSparkRepository @Inject constructor(
        private val allSparkService: AllSparkService,
) {
    suspend fun getAllSpark(): String? {
        val getAllSparkCall = allSparkService.getAllSpark()
        getAllSparkCall.let {
            if (it.code() == HttpURLConnection.HTTP_OK) {
                return it.body()
            }
        }
        return null
    }
}
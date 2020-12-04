package com.diegopardo.transformersbattle.repository

import com.diegopardo.transformersbattle.api.service.TransformersService
import com.diegopardo.transformersbattle.model.pojo.Transformer
import java.net.HttpURLConnection
import javax.inject.Inject

class TransformersRepository @Inject constructor(
    private val transformersService: TransformersService
) {

    suspend fun getTransformers(): List<Transformer>? {
        val response = transformersService.getTransformers()
        return if (response.code() == HttpURLConnection.HTTP_OK) {
            response.body()?.transformers?.map { transformer -> transformer.toTransformer() }
        } else {
            null
        }
    }
}
package com.diegopardo.transformersbattle.repository

import com.diegopardo.transformersbattle.api.service.TransformersService
import com.diegopardo.transformersbattle.model.dto.TransformerDTO
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

    suspend fun createTransformer(transformer: TransformerDTO): Transformer? {
        val response = transformersService.createTransformer(transformer)
        return if (response.code() == HttpURLConnection.HTTP_CREATED) {
            response.body()?.toTransformer()
        } else {
            null
        }
    }

    suspend fun updateTransformer(transformer: TransformerDTO): Transformer? {
        val response = transformersService.updateTransformer(transformer)
        return if (response.code() == HttpURLConnection.HTTP_OK) {
            response.body()?.toTransformer()
        } else {
            null
        }
    }
}
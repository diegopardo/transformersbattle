package com.diegopardo.transformersbattle.repository

import com.diegopardo.transformersbattle.api.service.TransformersService
import com.diegopardo.transformersbattle.model.dto.TransformerDTO
import java.net.HttpURLConnection
import javax.inject.Inject

class TransformersRepository @Inject constructor(
    private val transformersService: TransformersService
) {

    suspend fun getTransformers(): MutableList<TransformerDTO>? {
        val response = transformersService.getTransformers()
        return if (response.code() == HttpURLConnection.HTTP_OK) {
            response.body()?.transformers as MutableList<TransformerDTO>
        } else {
            null
        }
    }
}
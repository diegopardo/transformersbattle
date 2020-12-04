package com.diegopardo.transformersbattle.api.service

import com.diegopardo.transformersbattle.model.dto.TransformerDTO
import com.diegopardo.transformersbattle.model.dto.Transformers
import retrofit2.Response
import retrofit2.http.*

interface TransformersService {

    @GET("/transformers")
    suspend fun getTransformers(): Response<Transformers>

    @POST("/transformers")
    suspend fun createTransformer(@Body transformer: TransformerDTO): Response<TransformerDTO>

    @PUT("/transformers")
    suspend fun updateTransformer(@Body transformer: TransformerDTO): Response<TransformerDTO>

    @GET("/transformers/{transformerId}")
    suspend fun getTransformer()

    @DELETE("/transformers/{transformerId}")
    suspend fun deleteTransformer()
}
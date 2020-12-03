package com.diegopardo.transformersbattle.api.service

import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT

interface TransformersService {

    @GET("/transformers")
    suspend fun getTransformers()

    @POST("/transformers")
    suspend fun createTransformer()

    @PUT("/transformers")
    suspend fun updateTransformer()

    @GET("/transformers/{transformerId}")
    suspend fun getTransformer()

    @DELETE("/transformers/{transformerId}")
    suspend fun deleteTransformer()
}
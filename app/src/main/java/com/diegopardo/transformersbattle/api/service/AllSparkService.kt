package com.diegopardo.transformersbattle.api.service

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

interface AllSparkService {

    @GET("/allspark")
    fun getAllSpark(): Call<String>
}
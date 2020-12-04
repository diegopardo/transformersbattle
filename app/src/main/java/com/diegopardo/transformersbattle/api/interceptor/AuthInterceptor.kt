package com.diegopardo.transformersbattle.api.interceptor

import com.diegopardo.transformersbattle.api.service.AllSparkService
import com.diegopardo.transformersbattle.utils.ACCESS_TOKEN
import com.diegopardo.transformersbattle.utils.PreferencesHelper
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor @Inject constructor(
    private val preferencesHelper: PreferencesHelper,
    private val allSparkService: AllSparkService,
) : Interceptor{

    override fun intercept(chain: Interceptor.Chain): Response {
        verifyToken()
        val request = chain.request()
        val newRequest = request.newBuilder().addHeader("Accept", "*/*")
            .addHeader("Authorization", "Bearer ${preferencesHelper.token}").build()
            //.addHeader("Authorization", "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ0cmFuc2Zvcm1lcnNJZCI6Ii1NTmVuSzVVdDd4SUF1NWxvTkdMIiwiaWF0IjoxNjA3MDM1ODY3fQ.etm5dwr34JF8GsDof9B4erRqxj7eOJ3iptfHVV_VB3w").build()
        return chain.proceed(newRequest)
    }

    private fun verifyToken() {
        val accessToken = preferencesHelper.token
        if (accessToken.isNullOrEmpty()) {
            val response = allSparkService.getAllSpark().execute()
            response.body()?.let {
                preferencesHelper.save(ACCESS_TOKEN, it)
            }
        }
    }
}
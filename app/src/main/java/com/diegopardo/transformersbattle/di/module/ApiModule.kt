package com.diegopardo.transformersbattle.di.module

import com.diegopardo.transformersbattle.api.service.AllSparkService
import com.diegopardo.transformersbattle.api.service.TransformersService
import com.diegopardo.transformersbattle.utils.BASE_URL
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import javax.inject.Singleton

//@Module(includes = [AppModule::class])
@Module
class ApiModule {

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(ScalarsConverterFactory.create())
            //.addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideAllSparkService(retrofit: Retrofit): AllSparkService {
        return retrofit.create(AllSparkService::class.java)
    }

    @Singleton
    @Provides
    fun provideTransformersService(retrofit: Retrofit): TransformersService {
        return retrofit.create(TransformersService::class.java)
    }
}
package com.diegopardo.transformersbattle.di.module

import com.diegopardo.transformersbattle.BuildConfig
import com.diegopardo.transformersbattle.api.interceptor.AuthInterceptor
import com.diegopardo.transformersbattle.api.service.AllSparkService
import com.diegopardo.transformersbattle.api.service.TransformersService
import com.diegopardo.transformersbattle.api.util.TLSSocketFactory
import com.diegopardo.transformersbattle.utils.BASE_URL
import com.diegopardo.transformersbattle.utils.PreferencesHelper
import com.facebook.stetho.okhttp3.StethoInterceptor
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.security.KeyManagementException
import java.security.KeyStoreException
import java.security.NoSuchAlgorithmException
import javax.inject.Named
import javax.inject.Singleton


@Module
class ApiModule {

    @Singleton
    @Provides
    fun provideAllSparkRetrofit(): Retrofit {
        val okHttpClientBuilder = OkHttpClient.Builder()

        try {
            val tlsSocketFactory = TLSSocketFactory()
            if (tlsSocketFactory.trustManager != null) {
                okHttpClientBuilder
                    .sslSocketFactory(tlsSocketFactory, tlsSocketFactory.trustManager)
            }
        } catch (e: KeyManagementException) {
            e.printStackTrace()
        } catch (e: NoSuchAlgorithmException) {
            e.printStackTrace()
        } catch (e: KeyStoreException) {
            e.printStackTrace()
        }

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(ScalarsConverterFactory.create())
            .client(okHttpClientBuilder.build())
            .build()
    }

    @Singleton
    @Provides
    fun provideAllSparkService(retrofit: Retrofit): AllSparkService {
        return retrofit.create(AllSparkService::class.java)
    }

    @Singleton
    @Provides
    @Named("TransformersRetrofit")
    fun provideTransformersRetrofit(
        preferencesHelper: PreferencesHelper,
        allSparkService: AllSparkService,
    ): Retrofit {
        val okHttpClientBuilder = OkHttpClient.Builder().addInterceptor(
            AuthInterceptor(
                preferencesHelper,
                allSparkService
            )
        )

        try {
            val tlsSocketFactory = TLSSocketFactory()
            if (tlsSocketFactory.trustManager != null) {
                okHttpClientBuilder
                    .sslSocketFactory(tlsSocketFactory, tlsSocketFactory.trustManager)
            }
        } catch (e: KeyManagementException) {
            e.printStackTrace()
        } catch (e: NoSuchAlgorithmException) {
            e.printStackTrace()
        } catch (e: KeyStoreException) {
            e.printStackTrace()
        }

        if (BuildConfig.DEBUG) {
            okHttpClientBuilder.apply {
                addNetworkInterceptor(StethoInterceptor())
            }
        }

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClientBuilder.build())
            .build()
    }

    @Singleton
    @Provides
    fun provideTransformersService(@Named("TransformersRetrofit") retrofit: Retrofit): TransformersService {
        return retrofit.create(TransformersService::class.java)
    }
}
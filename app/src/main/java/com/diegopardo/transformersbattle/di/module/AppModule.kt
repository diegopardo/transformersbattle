package com.diegopardo.transformersbattle.di.module

import android.app.Application
import android.content.Context
import com.diegopardo.transformersbattle.utils.PreferencesHelper
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private val application: Application) {

    @Singleton
    @Provides
    fun provideContext(): Context = application.applicationContext

    @Singleton
    @Provides
    fun providePreferencesHelper(context: Context): PreferencesHelper =
        PreferencesHelper(context)
}
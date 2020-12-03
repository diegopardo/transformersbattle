package com.diegopardo.transformersbattle.application

import android.app.Application
import com.diegopardo.transformersbattle.BuildConfig
import com.diegopardo.transformersbattle.di.component.AppComponent
import com.diegopardo.transformersbattle.di.component.DaggerAppComponent
import com.diegopardo.transformersbattle.di.module.AppModule
import com.facebook.stetho.Stetho

open class TransformersBattleApp : Application() {

    val appComponent: AppComponent by lazy {
        initializeComponent()
    }

    open fun initializeComponent(): AppComponent =
        DaggerAppComponent.builder().appModule(AppModule(this)).build()

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Stetho.initializeWithDefaults(this)
        }
    }
}
package com.diegopardo.transformersbattle.application

import android.app.Application
import com.diegopardo.transformersbattle.di.component.AppComponent
import com.diegopardo.transformersbattle.di.component.DaggerAppComponent

open class TransformersBattleApp : Application() {

    val appComponent: AppComponent by lazy {
        initializeComponent()
    }

    open fun initializeComponent(): AppComponent = DaggerAppComponent .create()
}
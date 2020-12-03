package com.diegopardo.transformersbattle.di.component

import com.diegopardo.transformersbattle.MainActivity
import com.diegopardo.transformersbattle.di.module.ApiModule
import com.diegopardo.transformersbattle.di.module.AppModule
import com.diegopardo.transformersbattle.di.module.ContentModule
import com.diegopardo.transformersbattle.di.module.ViewModelModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, ApiModule::class, ViewModelModule::class])
interface AppComponent {

    fun contentComponent(): ContentComponent.Factory
    fun inject(activity: MainActivity)
}
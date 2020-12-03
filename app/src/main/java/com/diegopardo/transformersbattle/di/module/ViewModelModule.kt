package com.diegopardo.transformersbattle.di.module

import androidx.lifecycle.ViewModelProvider
import com.diegopardo.transformersbattle.di.viewmodel.ViewModelFactory
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}
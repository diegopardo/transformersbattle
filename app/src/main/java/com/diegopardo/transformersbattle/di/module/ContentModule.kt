package com.diegopardo.transformersbattle.di.module

import androidx.lifecycle.ViewModel
import com.diegopardo.transformersbattle.di.viewmodel.ViewModelKey
import com.diegopardo.transformersbattle.viewmodel.TransformersViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ContentModule() {

    @Binds
    @IntoMap
    @ViewModelKey(TransformersViewModel::class)
    abstract fun bindTransformersViewModel(transformersViewModel: TransformersViewModel): ViewModel
}
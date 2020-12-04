package com.diegopardo.transformersbattle.di.component

import com.diegopardo.transformersbattle.di.ActivityScoped
import com.diegopardo.transformersbattle.di.module.ContentModule
import com.diegopardo.transformersbattle.ui.fragment.CreateOrEditTransformerFragment
import com.diegopardo.transformersbattle.ui.fragment.TransformersFragment
import dagger.Subcomponent

@ActivityScoped
@Subcomponent(modules = [ContentModule::class])
interface ContentComponent {
    @Subcomponent.Factory
    interface Factory {
        fun create(): ContentComponent
    }

    fun inject(transformersFragment: TransformersFragment)
    fun inject(createOrEditTransformerFragment: CreateOrEditTransformerFragment)
}
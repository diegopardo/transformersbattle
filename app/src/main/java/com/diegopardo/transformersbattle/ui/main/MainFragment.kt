package com.diegopardo.transformersbattle.ui.main

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.diegopardo.transformersbattle.R
import com.diegopardo.transformersbattle.application.TransformersBattleApp
import com.diegopardo.transformersbattle.di.viewmodel.ViewModelFactory
import com.diegopardo.transformersbattle.viewmodel.TransformersViewModel
import javax.inject.Inject

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val transformersViewModel by lazy {
        ViewModelProvider(this, viewModelFactory).get(TransformersViewModel::class.java)
    }

    override fun onAttach(context: Context) {
        (context.applicationContext as TransformersBattleApp).appComponent.contentComponent().create()
            .inject(this)

        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        transformersViewModel.getTransformers()
    }

}
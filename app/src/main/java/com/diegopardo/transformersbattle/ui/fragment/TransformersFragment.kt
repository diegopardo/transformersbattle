package com.diegopardo.transformersbattle.ui.fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.diegopardo.transformersbattle.application.TransformersBattleApplication
import com.diegopardo.transformersbattle.databinding.FragmentTransformersBinding
import com.diegopardo.transformersbattle.di.viewmodel.ViewModelFactory
import com.diegopardo.transformersbattle.model.pojo.Transformer
import com.diegopardo.transformersbattle.ui.adapter.TransformersAdapter
import com.diegopardo.transformersbattle.ui.viewmodel.TransformersViewModel
import javax.inject.Inject

class TransformersFragment : Fragment() {

    companion object {
        fun newInstance() = TransformersFragment()
    }

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val transformersViewModel by lazy {
        ViewModelProvider(this, viewModelFactory).get(TransformersViewModel::class.java)
    }

    private lateinit var binding: FragmentTransformersBinding

    override fun onAttach(context: Context) {
        (context.applicationContext as TransformersBattleApplication).appComponent.contentComponent().create()
            .inject(this)

        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        binding = FragmentTransformersBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        initObservers()
        transformersViewModel.getTransformers()
    }

    private fun initObservers() {
        transformersViewModel.transformerList.observe(viewLifecycleOwner, {
            updateUI(it)
        })
        transformersViewModel.newTransformer.observe(viewLifecycleOwner, {
            updateUI(it)
        })
        transformersViewModel.updatedTransformer.observe(viewLifecycleOwner, {

        })
    }

    private fun updateUI(transformerList: List<Transformer>) {
        val transformersAdapter = TransformersAdapter(transformerList as MutableList<Transformer>)
        binding.transformersRecyclerView.apply {
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            adapter = transformersAdapter
        }
    }

    private fun updateUI(transformer: Transformer) {
        (binding.transformersRecyclerView.adapter as TransformersAdapter).addTransformer(transformer)
    }
}
package com.diegopardo.transformersbattle.ui.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.diegopardo.transformersbattle.application.TransformersBattleApplication
import com.diegopardo.transformersbattle.databinding.FragmentTransformersBinding
import com.diegopardo.transformersbattle.di.viewmodel.ViewModelFactory
import com.diegopardo.transformersbattle.model.pojo.Transformer
import com.diegopardo.transformersbattle.ui.adapter.SwipeToDeleteCallback
import com.diegopardo.transformersbattle.ui.adapter.TransformersAdapter
import com.diegopardo.transformersbattle.ui.viewmodel.TransformersViewModel
import com.diegopardo.transformersbattle.utils.ARG_TRANSFORMER
import javax.inject.Inject


class TransformersFragment : Fragment(), TransformersAdapter.OnItemClickListener {

    companion object {
        fun newInstance() = TransformersFragment()
    }

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val transformersViewModel by lazy {
        ViewModelProvider(activity as AppCompatActivity, viewModelFactory).get(TransformersViewModel::class.java)
    }

    private lateinit var binding: FragmentTransformersBinding

    override fun onAttach(context: Context) {
        (context.applicationContext as TransformersBattleApplication).appComponent.contentComponent().create()
            .inject(this)

        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
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

    private var swipedPosition: Int = -1

    private fun initObservers() {
        transformersViewModel.transformerList.observe(viewLifecycleOwner, {
            updateUI(it)
        })
        transformersViewModel.newTransformer.observe(viewLifecycleOwner, {
            (binding.transformersRecyclerView.adapter as TransformersAdapter).notifyTransformerInserted()
        })
        transformersViewModel.updatedTransformer.observe(viewLifecycleOwner, {
            (binding.transformersRecyclerView.adapter as TransformersAdapter).notifyTransformerUpdated(it)
        })
        transformersViewModel.deletedTransformer.observe(viewLifecycleOwner, {
            (binding.transformersRecyclerView.adapter as TransformersAdapter).notifyTransformerDeleted(swipedPosition)
        })
    }

    private fun updateUI(transformerList: List<Transformer>) {
        val transformersAdapter = TransformersAdapter(transformerList as MutableList<Transformer>, this)
        binding.transformersRecyclerView.apply {
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            adapter = transformersAdapter
            val swipeHandler = object : SwipeToDeleteCallback(context) {
                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                    swipedPosition = viewHolder.adapterPosition
                    val transformerId = (viewHolder as TransformersAdapter.ViewHolder).transformerId
                    transformersViewModel.deleteTransformer(transformerId)
                }
            }
            val itemTouchHelper = ItemTouchHelper(swipeHandler)
            itemTouchHelper.attachToRecyclerView(this)
        }
    }

    override fun onItemClicked(transformer: Transformer) {
        val bundle = Bundle()
        bundle.putParcelable(ARG_TRANSFORMER, transformer)
        val editTransformerFragment = CreateOrEditTransformerFragment.newInstance()
        editTransformerFragment.arguments = bundle
        editTransformerFragment.show(parentFragmentManager, CreateOrEditTransformerFragment.tag())
    }
}
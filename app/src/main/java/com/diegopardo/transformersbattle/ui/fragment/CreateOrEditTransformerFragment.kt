package com.diegopardo.transformersbattle.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.diegopardo.transformersbattle.databinding.FragmentCreateOrEditTransformerBinding

class CreateOrEditTransformerFragment : Fragment() {

    companion object {
        fun newInstance() = CreateOrEditTransformerFragment()
        fun tag() = CreateOrEditTransformerFragment.javaClass.canonicalName
    }

    private lateinit var binding: FragmentCreateOrEditTransformerBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        binding = FragmentCreateOrEditTransformerBinding.inflate(inflater)
        return binding.root
    }
}
package com.diegopardo.transformersbattle.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.diegopardo.transformersbattle.databinding.FragmentBattleBinding

class BattleFragment : Fragment() {

    companion object {
        fun newInstance() = BattleFragment()
        fun tag() = BattleFragment.javaClass.canonicalName
    }

    private lateinit var binding: FragmentBattleBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        binding = FragmentBattleBinding.inflate(inflater)
        return binding.root
    }
}
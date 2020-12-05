package com.diegopardo.transformersbattle.ui.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.diegopardo.transformersbattle.application.TransformersBattleApplication
import com.diegopardo.transformersbattle.databinding.FragmentBattleBinding
import com.diegopardo.transformersbattle.di.viewmodel.ViewModelFactory
import com.diegopardo.transformersbattle.ui.helper.BattleHelper
import com.diegopardo.transformersbattle.ui.viewmodel.TransformersViewModel
import javax.inject.Inject
import com.diegopardo.transformersbattle.R

class BattleFragment : DialogFragment() {

    companion object {
        fun newInstance() = BattleFragment()
        fun tag() = BattleFragment.javaClass.canonicalName
    }

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val transformersViewModel by lazy {
        ViewModelProvider(activity as AppCompatActivity, viewModelFactory).get(TransformersViewModel::class.java)
    }

    private lateinit var binding: FragmentBattleBinding

    override fun onAttach(context: Context) {
        (context.applicationContext as TransformersBattleApplication).appComponent.contentComponent().create()
            .inject(this)

        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        binding = FragmentBattleBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        initListeners()
        initObservers()
    }

    private fun initListeners() {
        binding.battleCancelBtn.setOnClickListener {
            dismiss()
        }
        binding.battleStartBtn.setOnClickListener {
            transformersViewModel.wageBattle()
        }
    }

    private fun initObservers() {
        transformersViewModel.battleResults.observe(viewLifecycleOwner, {
            showBattleResults(it)
        })
    }

    private fun showBattleResults(battleResults: BattleHelper.BattleResults) {
        binding.battleCancelBtn.text = getString(R.string.transformer_dialog_ok)
        binding.battleResultsBattleCount.text = resources.getQuantityString(R.plurals.transformers_battle_results_battle_count, battleResults.fightCount)
        binding.battleResultsBattleWinner.text = getString(R.string.transformers_battle_results_battle_winner, battleResults.getWinningTeam(), battleResults.getWinningTeamMembers())
        binding.battleResultsBattleLoser.text = getString(R.string.transformers_battle_results_battle_loser, battleResults.getLosingTeam(), battleResults.getLosingTeamMembers())
        binding.battleStartBtn.visibility = View.GONE
        binding.battleResults.visibility = View.VISIBLE
    }
}
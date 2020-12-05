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
import com.diegopardo.transformersbattle.databinding.FragmentCreateOrEditTransformerBinding
import com.diegopardo.transformersbattle.di.viewmodel.ViewModelFactory
import com.diegopardo.transformersbattle.model.dto.TransformerDTO
import com.diegopardo.transformersbattle.model.pojo.Transformer
import com.diegopardo.transformersbattle.ui.viewmodel.TransformersViewModel
import com.diegopardo.transformersbattle.utils.ARG_TRANSFORMER
import javax.inject.Inject
import kotlin.math.roundToInt

class CreateOrEditTransformerFragment : DialogFragment() {

    companion object {
        fun newInstance() = CreateOrEditTransformerFragment()
        fun tag(): String = CreateOrEditTransformerFragment.javaClass.canonicalName
    }

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val transformersViewModel by lazy {
        ViewModelProvider(activity as AppCompatActivity, viewModelFactory).get(TransformersViewModel::class.java)
    }

    private lateinit var binding: FragmentCreateOrEditTransformerBinding

    override fun onAttach(context: Context) {
        (context.applicationContext as TransformersBattleApplication).appComponent.contentComponent().create()
            .inject(this)

        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        binding = FragmentCreateOrEditTransformerBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val transformer = arguments?.getParcelable(ARG_TRANSFORMER) as Transformer?
        init(transformer)
    }

    private fun init(transformer: Transformer?) {
        transformer?.let {
            binding.createOrEditName.setText(transformer.name)
            if (transformer.team == "A") {
                binding.createOrEditTeamAutobots.isChecked = true
                binding.createOrEditTeamDecepticons.isChecked = false
            } else {
                binding.createOrEditTeamAutobots.isChecked = false
                binding.createOrEditTeamDecepticons.isChecked = true
            }
            binding.createOrEditStrength.value = transformer.strength.toFloat()
            binding.createOrEditIntelligence.value = transformer.intelligence.toFloat()
            binding.createOrEditSpeed.value = transformer.speed.toFloat()
            binding.createOrEditEndurance.value = transformer.endurance.toFloat()
            binding.createOrEditRank.value = transformer.rank.toFloat()
            binding.createOrEditCourage.value = transformer.courage.toFloat()
            binding.createOrEditFirepower.value = transformer.firepower.toFloat()
            binding.createOrEditSkill.value = transformer.skill.toFloat()
        }

        binding.createOrEditSaveBtn.setOnClickListener {
            // TODO: Validations

            val transformer = TransformerDTO(
                id = transformer?.id,
                name = binding.createOrEditName.text.toString(),
                team = if (binding.createOrEditTeamAutobots.isChecked) "A" else "D",
                strength = binding.createOrEditStrength.value.roundToInt(),
                intelligence = binding.createOrEditIntelligence.value.roundToInt(),
                speed = binding.createOrEditSpeed.value.roundToInt(),
                endurance = binding.createOrEditEndurance.value.roundToInt(),
                rank = binding.createOrEditRank.value.roundToInt(),
                courage = binding.createOrEditCourage.value.roundToInt(),
                firepower = binding.createOrEditFirepower.value.roundToInt(),
                skill = binding.createOrEditSkill.value.roundToInt(),
                team_icon = null
            )

            if (transformer.id.isNullOrEmpty()) {
                transformersViewModel.createTransformer(transformer)
            } else {
                transformersViewModel.updateTransformer(transformer)
            }

            dismiss()
        }

        binding.createOrEditCancelBtn.setOnClickListener { dismiss() }
    }
}
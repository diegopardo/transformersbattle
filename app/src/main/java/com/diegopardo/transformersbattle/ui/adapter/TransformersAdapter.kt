package com.diegopardo.transformersbattle.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.diegopardo.transformersbattle.databinding.RecyclerViewItemTransformerBinding
import com.diegopardo.transformersbattle.model.pojo.Transformer

class TransformersAdapter(
    private val transformerList: MutableList<Transformer>,
    private val onItemClickListener: OnItemClickListener,
) : RecyclerView.Adapter<TransformersAdapter.ViewHolder>() {

    class ViewHolder(val binding: RecyclerViewItemTransformerBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val binding = RecyclerViewItemTransformerBinding.inflate(
            LayoutInflater.from(viewGroup.context),
            viewGroup,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val transformer = transformerList[position]
        Glide.with(viewHolder.binding.root.context).load(transformer.team_icon)
            .into(viewHolder.binding.transformerGroupIcon)
        viewHolder.binding.transformerName.text = transformer.name
        viewHolder.binding.transformerOverallRating.text = transformer.getOverallRating().toString()
        viewHolder.binding.transformerStrength.text = transformer.strength.toString()
        viewHolder.binding.transformerIntelligence.text = transformer.intelligence.toString()
        viewHolder.binding.transformerSpeed.text = transformer.speed.toString()
        viewHolder.binding.transformerEndurance.text = transformer.endurance.toString()
        viewHolder.binding.transformerFirepower.text = transformer.firepower.toString()
        viewHolder.binding.root.setOnClickListener {
            onItemClickListener.onItemClicked(transformer)
        }
    }

    override fun getItemCount() = transformerList.size

    fun addTransformer(transformer: Transformer) {
        transformerList.add(transformer)
        notifyItemInserted(transformerList.size)
    }

    interface OnItemClickListener {
        fun onItemClicked(transformer: Transformer)
    }
}
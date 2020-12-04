package com.diegopardo.transformersbattle.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.diegopardo.transformersbattle.R
import com.diegopardo.transformersbattle.model.pojo.Transformer

class TransformersAdapter(private val transformerList: MutableList<Transformer>) :
    RecyclerView.Adapter<TransformersAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView = view.findViewById(R.id.transformer_name)

    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.recycler_view_item_transformer, viewGroup, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.textView.text = transformerList[position].name
    }

    override fun getItemCount() = transformerList.size

}
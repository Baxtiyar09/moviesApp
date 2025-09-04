package com.example.atlmovaapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.atlmovaapp.databinding.ItemCreditsBinding
import com.example.atlmovaapp.model.detail.flimCredits.Cast

class CreditsAdapter : RecyclerView.Adapter<CreditsAdapter.CreditsViewHolder>(){
    val credits = arrayListOf<Cast>()
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CreditsViewHolder {
        val view = ItemCreditsBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return CreditsViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: CreditsViewHolder,
        position: Int
    ) {
        val item = credits[position]
        holder.itemCreditsBinding.credits = item
    }

    override fun getItemCount(): Int {
        return credits.size
    }

    fun updateCredits(newCredits: List<Cast>){
        credits.clear()
        credits.addAll(newCredits)
        notifyDataSetChanged()
    }

    class CreditsViewHolder(val itemCreditsBinding: ItemCreditsBinding) :
        RecyclerView.ViewHolder(itemCreditsBinding.root)
}
package com.example.atlmovaapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.atlmovaapp.databinding.ItemTrailersBinding
import com.example.atlmovaapp.model.detail.trailers.ResultTrailers

class TrailersAdapter : RecyclerView.Adapter<TrailersAdapter.TrailersViewHolder>() {

    lateinit var onItemClick: (ResultTrailers) -> Unit

    val trailers = arrayListOf<ResultTrailers>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TrailersViewHolder {
        val view = ItemTrailersBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TrailersViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: TrailersViewHolder,
        position: Int
    ) {
        val item = trailers[position]
        holder.itemTrailersBinding.trailer = item

        holder.itemTrailersBinding.trailerCard.setOnClickListener {
            onItemClick.invoke(item)
        }
    }

    override fun getItemCount(): Int {
        return trailers.size
    }

    fun updateTrailers(newTrailers: List<ResultTrailers>) {
        trailers.clear()
        trailers.addAll(newTrailers)
        notifyDataSetChanged()
    }

    class TrailersViewHolder(val itemTrailersBinding: ItemTrailersBinding) :
        RecyclerView.ViewHolder(itemTrailersBinding.root)
}
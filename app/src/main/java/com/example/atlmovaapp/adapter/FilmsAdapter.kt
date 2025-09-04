package com.example.atlmovaapp.adapter

import android.graphics.Movie
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.atlmovaapp.databinding.ItemFilmsBinding
import com.example.atlmovaapp.model.Result

class FilmsAdapter(
    private val onItemClick: (Int) -> Unit
): RecyclerView.Adapter<FilmsAdapter.FilmsViewHolder>() {

    val flims = arrayListOf<Result>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FilmsViewHolder {
        val view = ItemFilmsBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return FilmsViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: FilmsViewHolder,
        position: Int
    ) {
        val item = flims[position]
        holder.itemFilmsBinding.mova = item


        holder.itemFilmsBinding.root.setOnClickListener {
            item.id?.let {
                onItemClick(item.id)
            }
        }
    }

    override fun getItemCount(): Int {
        return flims.size

    }

    fun updateList(newList: List<Result>) {
        flims.clear()
        flims.addAll(newList)
        notifyDataSetChanged()
    }

    class FilmsViewHolder(val itemFilmsBinding: ItemFilmsBinding) :
        RecyclerView.ViewHolder(itemFilmsBinding.root)
}
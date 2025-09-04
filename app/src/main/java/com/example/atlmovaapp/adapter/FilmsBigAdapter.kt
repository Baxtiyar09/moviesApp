package com.example.atlmovaapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.atlmovaapp.databinding.ItemFilmsBigBinding
import com.example.atlmovaapp.model.Result

class FilmsBigAdapter : RecyclerView.Adapter<FilmsBigAdapter.FilmsBigViewHolder>(){

    val flims = arrayListOf<Result>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FilmsBigViewHolder {
        val view = ItemFilmsBigBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return FilmsBigViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: FilmsBigViewHolder,
        position: Int
    ) {
       val item = flims[position]
        holder.itemFlimsBigBinding.film = item
    }

    override fun getItemCount(): Int {
        return flims.size
    }

    fun updateList(newList: List<Result>){
        flims.clear()
        flims.addAll(newList)
        notifyDataSetChanged()
    }

    class FilmsBigViewHolder(val itemFlimsBigBinding: ItemFilmsBigBinding) :
        RecyclerView.ViewHolder(itemFlimsBigBinding.root)
}
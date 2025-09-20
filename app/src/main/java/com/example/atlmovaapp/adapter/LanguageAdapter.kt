package com.example.atlmovaapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.atlmovaapp.R
import com.example.atlmovaapp.databinding.ItemLanguageBinding
import com.example.atlmovaapp.model.database.CardModel
import com.example.atlmovaapp.model.language.LanguageModel

class LanguageAdapter(
    private val onItemClick: (LanguageModel) -> Unit
) : RecyclerView.Adapter<LanguageAdapter.LanguageViewHolder>() {
    val languageList = arrayListOf<LanguageModel>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): LanguageViewHolder {
        val view = ItemLanguageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return LanguageViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: LanguageViewHolder,
        position: Int
    ) {
        val item = languageList[position]
        holder.itemLanguageBinding.language = item




        holder.itemLanguageBinding.imageViewRadioFalse.setImageResource(
            if (item.selected) R.drawable.radiotrue else R.drawable.radiofalse
        )

        holder.itemLanguageBinding.root.setOnClickListener {
            languageList.forEach { it.selected = false }
            item.selected = true
            notifyDataSetChanged()
            onItemClick(item)
        }
    }

    override fun getItemCount(): Int {
        return languageList.size
    }

    fun updateList(newList: List<LanguageModel>) {
        this.languageList.clear()
        this.languageList.addAll(newList)
        notifyDataSetChanged()
    }

    fun clearSelection() {
        languageList.forEach { it.selected = false }
        notifyDataSetChanged()
    }

    class LanguageViewHolder(val itemLanguageBinding: ItemLanguageBinding) :
        RecyclerView.ViewHolder(itemLanguageBinding.root)
}
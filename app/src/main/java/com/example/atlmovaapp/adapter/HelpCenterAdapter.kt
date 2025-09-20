package com.example.atlmovaapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.atlmovaapp.databinding.ItemFagBinding
import com.example.atlmovaapp.databinding.ItemHelpContactBinding
import com.example.atlmovaapp.model.help_center.contactUs.HelpContactUsModel
import com.example.atlmovaapp.model.help_center.fag.HelpFagModel

class HelpCenterAdapter : RecyclerView.Adapter<HelpCenterAdapter.HelpCenterViewHolder>() {
    val fagList = arrayListOf<HelpFagModel>()
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HelpCenterViewHolder {
        val view = ItemFagBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HelpCenterViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: HelpCenterViewHolder,
        position: Int
    ) {
       val item = fagList[position]
        holder.itemFagBinding.fag = item
    }

    override fun getItemCount(): Int {
        return fagList.size
    }

    fun updateList(list: List<HelpFagModel>){
        fagList.clear()
        fagList.addAll(list)
        notifyDataSetChanged()
    }

    class HelpCenterViewHolder(val itemFagBinding: ItemFagBinding) :
        RecyclerView.ViewHolder(itemFagBinding.root)

}
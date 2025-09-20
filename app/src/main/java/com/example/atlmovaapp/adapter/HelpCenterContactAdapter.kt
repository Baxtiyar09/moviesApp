package com.example.atlmovaapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.atlmovaapp.databinding.ItemHelpContactBinding
import com.example.atlmovaapp.model.help_center.contactUs.HelpContactUsModel

class HelpCenterContactAdapter  : RecyclerView.Adapter<HelpCenterContactAdapter.HelpCenterContactViewHolder>(){
    val contactList = arrayListOf<HelpContactUsModel>()
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HelpCenterContactViewHolder {
        val view = ItemHelpContactBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HelpCenterContactViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: HelpCenterContactViewHolder,
        position: Int
    ) {
       val item = contactList[position]
        holder.itemHelpContactBinding.contact = item
    }

    override fun getItemCount(): Int {
        return contactList.size
    }

    fun updateList(list: List<HelpContactUsModel>){
        contactList.clear()
        contactList.addAll(list)
        notifyDataSetChanged()
    }

    class HelpCenterContactViewHolder(val itemHelpContactBinding: ItemHelpContactBinding) :
        RecyclerView.ViewHolder(itemHelpContactBinding.root)
}
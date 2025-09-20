package com.example.atlmovaapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.atlmovaapp.databinding.ItemPrivacyPolicyBinding
import com.example.atlmovaapp.model.privacy_policy.PrivacyPolicyModel

class PrivacyPolicyAdapter : RecyclerView.Adapter<PrivacyPolicyAdapter.PrivacyPolicyViewHolder>() {
    val list = arrayListOf<PrivacyPolicyModel>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PrivacyPolicyViewHolder {
        val view =
            ItemPrivacyPolicyBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PrivacyPolicyViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: PrivacyPolicyViewHolder,
        position: Int
    ) {
        val item = list[position]
        holder.itemPrivacyPolicyBinding.privacyPolicyModel = item
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun updateList(newList: List<PrivacyPolicyModel>) {
        this.list.clear()
        this.list.addAll(newList)
        notifyDataSetChanged()
    }

    class PrivacyPolicyViewHolder(val itemPrivacyPolicyBinding: ItemPrivacyPolicyBinding) :
        RecyclerView.ViewHolder(itemPrivacyPolicyBinding.root)
}
package com.example.atlmovaapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.atlmovaapp.databinding.ItemOnboardingPagerBinding

class OnboardingPagerAdapter :
    RecyclerView.Adapter<OnboardingPagerAdapter.OnboardingPagerViewHolder>() {
        val list = arrayListOf<String>()
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): OnboardingPagerViewHolder {
        val view =
            ItemOnboardingPagerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return OnboardingPagerViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: OnboardingPagerViewHolder,
        position: Int
    ) {
        val item = list[position]
        holder.itemOnboardingPagerBinding.onboardingWords = item
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun updateList(newList: List<String>) {
        list.clear()
        list.addAll(newList)
        notifyDataSetChanged()
    }

    class OnboardingPagerViewHolder(val itemOnboardingPagerBinding: ItemOnboardingPagerBinding) :
        RecyclerView.ViewHolder(itemOnboardingPagerBinding.root)
}
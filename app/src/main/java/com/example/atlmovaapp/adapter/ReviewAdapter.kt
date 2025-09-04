package com.example.atlmovaapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.atlmovaapp.databinding.ItemReviewsBinding
import com.example.atlmovaapp.model.detail.reviews.ResultReviews

class ReviewAdapter : RecyclerView.Adapter<ReviewAdapter.ReviewViewHolder>(){
    val reviews = arrayListOf<ResultReviews>()
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ReviewViewHolder {
        val view = ItemReviewsBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ReviewViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: ReviewViewHolder,
        position: Int
    ) {
        val item = reviews[position]
        holder.itemReviewsBinding.comment = item
        holder.itemReviewsBinding.executePendingBindings()

    }

    override fun getItemCount(): Int {
       return reviews.size
    }

    fun updateList(newList: List<ResultReviews>){
        reviews.clear()
        reviews.addAll(newList)
        notifyDataSetChanged()
    }

    class ReviewViewHolder(val itemReviewsBinding: ItemReviewsBinding) :
        RecyclerView.ViewHolder(itemReviewsBinding.root)
}
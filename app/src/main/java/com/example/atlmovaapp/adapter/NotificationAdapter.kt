package com.example.atlmovaapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.atlmovaapp.databinding.ItemNotificationBinding
import com.example.atlmovaapp.model.Result

class NotificationAdapter(
    private val onItemClick: (Int) -> Unit
) : RecyclerView.Adapter<NotificationAdapter.NotificationViewHolder>() {
    val notificationList = arrayListOf<Result>()
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): NotificationViewHolder {
        val view =
            ItemNotificationBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NotificationViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: NotificationViewHolder,
        position: Int
    ) {
        val item = notificationList[position]
        holder.itemNotificationBinding.film = item

        holder.itemNotificationBinding.root.setOnClickListener {
            item.id?.let {
                onItemClick(item.id)
            }
        }
    }

    override fun getItemCount(): Int {
        return notificationList.size
    }

    fun updateList(newList: List<Result>) {
        notificationList.clear()
        notificationList.addAll(newList)
        notifyDataSetChanged()
    }

    class NotificationViewHolder(val itemNotificationBinding: ItemNotificationBinding) :
        RecyclerView.ViewHolder(itemNotificationBinding.root)
}
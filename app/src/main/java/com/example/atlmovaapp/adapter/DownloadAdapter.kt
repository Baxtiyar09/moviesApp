package com.example.atlmovaapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.atlmovaapp.databinding.ItemDownloadBinding
import com.example.atlmovaapp.model.Result

class DownloadAdapter(
    private val onDeleteItemClick: (Int) -> Unit,
    private val onItemClick: (Int) -> Unit
) : RecyclerView.Adapter<DownloadAdapter.DownloadViewHolder>() {

    val downloadList = ArrayList<Result>()
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DownloadViewHolder {
        val view = ItemDownloadBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DownloadViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: DownloadViewHolder,
        position: Int
    ) {
        val item = downloadList[position]
        holder.itemDownloadBinding.film = item

        holder.itemDownloadBinding.deleteAllDownloadsIcon.setOnClickListener {
            item.id?.let {
                onDeleteItemClick(item.id)
            }
        }

        holder.itemDownloadBinding.root.setOnClickListener {
            item.id?.let {
                onItemClick(item.id)
            }
        }
    }

    override fun getItemCount(): Int {
        return downloadList.size
    }

    fun updateList(newList: List<Result>) {
        downloadList.clear()
        downloadList.addAll(newList)
        notifyDataSetChanged()
    }

    class DownloadViewHolder(val itemDownloadBinding: ItemDownloadBinding) :
        RecyclerView.ViewHolder(itemDownloadBinding.root)
}
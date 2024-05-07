package com.example.nbc_search.presentation.ui.storage

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.nbc_search.FormatManager
import com.example.nbc_search.databinding.ItemStorageBinding
import com.example.nbc_search.presentation.model.SearchModel
import com.example.nbc_search.presentation.ui.search.OnClickListener

class StorageAdapter(private val items: List<SearchModel>, private val listener: OnClickListener) : RecyclerView.Adapter<StorageAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemStorageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position], listener)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class ViewHolder(private val binding: ItemStorageBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: SearchModel, listener: OnClickListener) {
            binding.apply {
                Glide.with(ivArea.context)
                    .load(item.thumbnailUrl)
                    .into(ivArea)
                tvSite.text = item.siteName
                tvDate.text = FormatManager.dateFormat(item.dateTime)

                itemArea.setOnClickListener {
                    listener.onItemClick(adapterPosition)
                }
            }
        }
    }
}
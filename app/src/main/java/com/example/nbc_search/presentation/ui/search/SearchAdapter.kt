package com.example.nbc_search.presentation.ui.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.nbc_search.FormatManager
import com.example.nbc_search.databinding.ItemSearchBinding
import com.example.nbc_search.presentation.model.SearchModel

class SearchAdapter(private var items: List<SearchModel>) : RecyclerView.Adapter<SearchAdapter.ViewHolder>() {

    fun updateItems(newItems: List<SearchModel>) {
        items = newItems
        notifyDataSetChanged()
        //notifyItemInserted()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemSearchBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class ViewHolder(private val binding: ItemSearchBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: SearchModel) {
            binding.apply {
                Glide.with(ivArea.context)
                    .load(item.thumbnailUrl)
                    .into(ivArea)
                tvSite.text = item.siteName
                tvDate.text = FormatManager.dateFormat(item.dateTime)
            }
        }
    }
}
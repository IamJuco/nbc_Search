package com.example.nbc_search.ui.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.nbc_search.FormatManager
import com.example.nbc_search.databinding.ItemSearchBinding
import com.example.nbc_search.model.SearchModel

class SearchAdapter(private val items: List<SearchModel>) : RecyclerView.Adapter<SearchAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchAdapter.ViewHolder {
        val binding = ItemSearchBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SearchAdapter.ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class ViewHolder(private val binding: ItemSearchBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: SearchModel) {
            binding.apply {
                ivArea.setImageResource(item.thumbnailUrl)
                tvSite.text = item.siteName
                tvDate.text = item.dateTime.let { FormatManager.dateFormat(it) }
                // 클릭 이벤트 설정할것
//                ivFavorite.setImageResource(item.favorite)
            }
        }
    }
}
package com.example.nbc_search.presentation.ui.search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.nbc_search.Constants
import com.example.nbc_search.FormatManager
import com.example.nbc_search.databinding.ItemSearchBinding
import com.example.nbc_search.data.db.DBManager
import com.example.nbc_search.presentation.model.SearchModel
import com.example.nbc_search.presentation.util.OnClickListener

class SearchAdapter(private var items: List<SearchModel>, private val listener: OnClickListener) : RecyclerView.Adapter<SearchAdapter.ViewHolder>() {

    fun updateItems(newItems: List<SearchModel>) {
        items = newItems
        notifyDataSetChanged()
        //notifyItemInserted()
    }

    fun getItem(position: Int): SearchModel {
        return items[position]
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemSearchBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position], listener)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class ViewHolder(private val binding: ItemSearchBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: SearchModel, listener: OnClickListener) {
            binding.apply {
                Glide.with(ivArea.context)
                    .load(item.thumbnailUrl)
                    .into(ivArea)
                tvSite.text = item.siteName
                tvDate.text = FormatManager.dateFormat(item.dateTime)

                updateFavorite(item.favorite)

                itemArea.setOnClickListener {
                    item.favorite = !item.favorite
                    updateFavorite(item.favorite)
                    listener.onItemClick(adapterPosition)

                    if (item.favorite) {
                        DBManager.saveData(ivArea.context, item.thumbnailUrl, item, Constants.FAVORITE_DATA)
                    }
                    else {
                        DBManager.removeData(ivArea.context, item.thumbnailUrl, Constants.FAVORITE_DATA)
                    }
                }
            }
        }
        private fun updateFavorite(isFavorite: Boolean) {
            if (isFavorite) {
                binding.ivFavorite.visibility = View.VISIBLE
            } else {
                binding.ivFavorite.visibility = View.GONE
            }
        }
    }
}
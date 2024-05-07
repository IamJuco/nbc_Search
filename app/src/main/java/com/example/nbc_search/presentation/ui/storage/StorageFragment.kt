package com.example.nbc_search.presentation.ui.storage

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.example.nbc_search.Constants
import com.example.nbc_search.R
import com.example.nbc_search.databinding.FragmentStorageBinding
import com.example.nbc_search.presentation.mapper.ImageMapper
import com.example.nbc_search.presentation.model.SearchModel
import com.example.nbc_search.presentation.ui.search.OnClickListener

class StorageFragment : Fragment(), OnClickListener {

    private lateinit var binding: FragmentStorageBinding
    private lateinit var storageAdapter: StorageAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentStorageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupAdapter()

    }

    override fun onItemClick(position: Int) {
        removeDialog()
    }

    private fun setupAdapter() {
        val favoriteItems = loadData(requireContext(), Constants.FAVORITE_DATA)
        storageAdapter = StorageAdapter(favoriteItems, this)
        binding.rvStorage.adapter = storageAdapter
        binding.rvStorage.layoutManager = GridLayoutManager(context, 2)
    }

    private fun loadData(context: Context, name: String): List<SearchModel> {
        val pref = context.getSharedPreferences(name, Context.MODE_PRIVATE)
        return pref.all.mapNotNull { (key, value) ->
            value?.let { ImageMapper.loadData(context, key, SearchModel::class.java, name) }
        }
    }

    private fun removeDialog() {
        val builder = AlertDialog.Builder(context)
        builder
            .setTitle("좋아요 해제")
            .setMessage("정말 좋아요 해지를 하시겠습니까?")
            .setIcon(R.drawable.ic_favorite)
            .setPositiveButton("예") { _, _ ->

            }
            .setNegativeButton("아니요") { dialog, _ ->
                dialog.dismiss()
            }
            .create()
            .show()
    }


}
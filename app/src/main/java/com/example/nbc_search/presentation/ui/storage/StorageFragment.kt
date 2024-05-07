package com.example.nbc_search.presentation.ui.storage

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.example.nbc_search.Constants
import com.example.nbc_search.databinding.FragmentStorageBinding
import com.example.nbc_search.presentation.mapper.ImageMapper
import com.example.nbc_search.presentation.model.SearchModel

class StorageFragment : Fragment() {

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

    private fun setupAdapter() {
        val favoriteItems = loadData(requireContext(), Constants.FAVORITE_DATA)
        storageAdapter = StorageAdapter(favoriteItems)
        binding.rvStorage.adapter = storageAdapter
        binding.rvStorage.layoutManager = GridLayoutManager(context, 2)
    }

    private fun loadData(context: Context, name: String): List<SearchModel> {
        val pref = context.getSharedPreferences(name, Context.MODE_PRIVATE)
        return pref.all.mapNotNull { (key, value) ->
            value?.let { ImageMapper.loadData(context, key, SearchModel::class.java, name) }
        }
    }
}
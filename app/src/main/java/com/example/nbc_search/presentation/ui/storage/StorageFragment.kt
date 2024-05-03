package com.example.nbc_search.presentation.ui.storage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.example.nbc_search.R
import com.example.nbc_search.databinding.FragmentStorageBinding

class StorageFragment : Fragment() {

    private lateinit var binding: FragmentStorageBinding
    private lateinit var storageAdapter: StorageAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_storage, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        setupAdapter()

    }

    private fun setupAdapter() {
        storageAdapter = StorageAdapter(emptyList())
        binding.rvStorage.adapter = storageAdapter
        binding.rvStorage.layoutManager = GridLayoutManager(context, 2)
    }

}
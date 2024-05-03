package com.example.nbc_search.presentation.ui.search

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.nbc_search.databinding.FragmentSearchBinding
import com.example.nbc_search.network.RetrofitClient
import com.example.nbc_search.presentation.model.SearchModel
import kotlinx.coroutines.launch
import java.util.Date

class SearchFragment : Fragment() {

    private lateinit var binding: FragmentSearchBinding

    private lateinit var searchAdapter: SearchAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupAdapter()
        setupListener()

    }

    private fun setupAdapter() {
        searchAdapter = SearchAdapter(emptyList())
        binding.rvSearch.adapter = searchAdapter
        binding.rvSearch.layoutManager = GridLayoutManager(context, 2)
    }

    private fun setupListener() {
        binding.ivSearchMove.setOnClickListener {
            val query = binding.etSearchArea.text.toString()
            if (query.isNotEmpty()) {
                searchImages(query)
            }
            keyboardHide()
        }
    }

    private fun searchImages(query: String) {
        lifecycleScope.launch {
            val response = RetrofitClient.searchImageRetrofit.searchImage(query)
            if (response.documents != null) {
                updateAdapter(response.documents.map {
                    SearchModel(
                        thumbnailUrl = it.thumbnailUrl ?: "",
                        siteName = it.displaySitename ?: "",
                        dateTime = it.datetime ?: Date(),
                        favorite = 0
                    )
                })
            }
        }
    }

    private fun keyboardHide() {
        val inputMethodManager = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
        inputMethodManager?.hideSoftInputFromWindow(view?.windowToken, 0)
    }

    private fun updateAdapter(items: List<SearchModel>) {
        searchAdapter.updateItems(items)
    }
}
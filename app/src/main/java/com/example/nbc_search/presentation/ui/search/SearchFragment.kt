package com.example.nbc_search.presentation.ui.search

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.nbc_search.R
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
            val searchArea = binding.etSearchArea.text.toString()
            if (searchArea.isNotEmpty()) {
                searchImages(searchArea)
            } else {
                Toast.makeText(context, getString(R.string.fragment_search_toast_message), Toast.LENGTH_SHORT).show()
            }
            keyboardHide()
        }
    }

    private fun searchImages(query: String) {
        // 프래그먼트가 파괴될때 자동으로 실행 중단 ( 메모리 누수 방지 )
        lifecycleScope.launch {
            // RetrofitClient에 접근및 검색어를 쿼리 파라미터중 query에 전달
            val response = RetrofitClient.searchImageRetrofit.searchImage(query)
            // SearchImageResponse의 documents에 접근
            // documents는 ImageDocumentResponse를 리스트 형태로 가지고있음 ( 각 이미지에 대한 상세 정보 )
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
package com.example.nbc_search.presentation.ui.search

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.nbc_search.R
import com.example.nbc_search.databinding.FragmentSearchBinding
import com.example.nbc_search.presentation.util.OnClickListener

class SearchFragment : Fragment(), OnClickListener {

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!
    private lateinit var searchAdapter: SearchAdapter
    private val viewModel: SearchViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupAdapter()
        setupListener()
        setupObservers()
    }

    override fun onItemClick(position: Int) {
        val item = searchAdapter.getItem(position)
    }

    private fun setupAdapter() {
        searchAdapter = SearchAdapter(emptyList(), this)
        binding.rvSearch.adapter = searchAdapter
        binding.rvSearch.layoutManager = GridLayoutManager(context, 2)
    }

    private fun setupObservers() {
        viewModel.searchData.observe(viewLifecycleOwner) { data ->
            searchAdapter.updateItems(data)
        }
        viewModel.loadSearchData(requireContext())?.let {
            binding.etSearchArea.setText(it)
        }
    }

    private fun setupListener() {
        binding.ivSearchMove.setOnClickListener {
            val searchArea = binding.etSearchArea.text.toString()
            if (searchArea.isNotEmpty()) {
                viewModel.searchImages(searchArea)
                viewModel.saveSearchData(requireContext(), searchArea)
            } else {
                Toast.makeText(context, getString(R.string.fragment_search_toast_message), Toast.LENGTH_SHORT).show()
            }
            keyboardHide()
        }
    }

    private fun keyboardHide() {
        val inputMethodManager = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
        inputMethodManager?.hideSoftInputFromWindow(view?.windowToken, 0)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
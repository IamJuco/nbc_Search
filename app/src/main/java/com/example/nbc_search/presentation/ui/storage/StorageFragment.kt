package com.example.nbc_search.presentation.ui.storage

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.example.nbc_search.Constants
import com.example.nbc_search.R
import com.example.nbc_search.databinding.FragmentStorageBinding
import com.example.nbc_search.presentation.db.DBManager
import com.example.nbc_search.presentation.model.SearchModel
import com.example.nbc_search.presentation.ui.search.OnClickListener

class StorageFragment : Fragment(), OnClickListener {

    private var _binding: FragmentStorageBinding? = null
    private val binding get() = _binding!!
    private lateinit var storageAdapter: StorageAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentStorageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupAdapter()

    }

    override fun onItemClick(position: Int) {
        val thumbnailUrl = storageAdapter.getThumbnailUrl(position)
        removeDialog(thumbnailUrl)
    }

    // onResume에서 데이터 갱신
    // StorageFragment 화면에 나타날 때마다 (onResume) 에서 데이터 갱신하는 방법
    override fun onResume() {
        super.onResume()
        updateData()
        Log.d("Resume", "ResumeCalled")
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
            value?.let { DBManager.loadData(context, key, SearchModel::class.java, name) }
        }
    }

    private fun removeDialog(thumbnailUrl: String) {
        val builder = AlertDialog.Builder(context)
        builder
            .setTitle("좋아요 해제")
            .setMessage("정말 좋아요를 해제 하시겠습니까?")
            .setIcon(R.drawable.ic_favorite)
            .setPositiveButton("예") { _, _ ->
                context?.let { DBManager.removeData(it,thumbnailUrl, Constants.FAVORITE_DATA ) }
                updateData()
            }
            .setNegativeButton("아니요") { dialog, _ ->
                dialog.dismiss()
            }
            .create()
            .show()
    }

    private fun updateAdapter(items: List<SearchModel>) {
        storageAdapter.updateItems(items)
    }

    private fun updateData() {
        val favoriteItems = loadData(requireContext(), Constants.FAVORITE_DATA)
        updateAdapter(favoriteItems)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}
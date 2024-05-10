package com.example.nbc_search.presentation.ui.search

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import com.example.nbc_search.Constants
import com.example.nbc_search.network.RetrofitClient
import com.example.nbc_search.presentation.db.DBManager
import com.example.nbc_search.presentation.model.SearchModel
import kotlinx.coroutines.launch
import java.util.Date

class SearchViewModel : ViewModel() {

    private val _searchData: MutableLiveData<List<SearchModel>> = MutableLiveData()
    val searchData: LiveData<List<SearchModel>> get() = _searchData

    fun searchImages(query: String) {
        // 프래그먼트가 파괴될때 자동으로 실행 중단 ( 메모리 누수 방지 )
        viewModelScope.launch {

            // RetrofitClient에 접근및 검색어를 쿼리 파라미터중 query에 전달
            val response = RetrofitClient.searchImageRetrofit.searchImage(query)

            // SearchImageResponse의 documents에 접근
            // documents는 ImageDocumentResponse를 리스트 형태로 가지고있음 ( 각 이미지에 대한 상세 정보 )
            if (response.documents != null) {
                _searchData.value = response.documents.map {
                    SearchModel(
                        thumbnailUrl = it.thumbnailUrl ?: "",
                        siteName = it.displaySitename ?: "",
                        dateTime = it.datetime ?: Date(),
                        favorite = false
                    )
                }
            }
        }
    }

    fun saveSearchData(context: Context, query: String) {
        DBManager.saveData(context, Constants.SEARCH_DATA_KEY, query, Constants.SEARCH_DATA)
    }

    fun loadSearchData(context: Context): String? {
        return DBManager.loadData(context, Constants.SEARCH_DATA_KEY, String::class.java, Constants.SEARCH_DATA)
    }

}
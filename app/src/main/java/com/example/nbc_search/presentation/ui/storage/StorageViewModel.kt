package com.example.nbc_search.presentation.ui.storage

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.nbc_search.Constants
import com.example.nbc_search.presentation.db.DBManager
import com.example.nbc_search.presentation.model.SearchModel

class StorageViewModel : ViewModel() {

    private val _storageData: MutableLiveData<List<SearchModel>> = MutableLiveData()
    val storageData: LiveData<List<SearchModel>> get() = _storageData

    fun loadData(context: Context, name: String) {
        val pref = context.getSharedPreferences(name, Context.MODE_PRIVATE)
        val items = pref.all.mapNotNull { (key, value) ->
            value?.let { DBManager.loadData(context, key, SearchModel::class.java, name) }
        }
        _storageData.value = items
    }

    fun removeFavoriteData(context: Context, key: String, name: String) {
        DBManager.removeData(context, key, name)
        loadData(context, Constants.FAVORITE_DATA)
    }



}
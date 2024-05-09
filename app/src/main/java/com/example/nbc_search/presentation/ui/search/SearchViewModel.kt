package com.example.nbc_search.presentation.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SearchViewModel : ViewModel() {

    private val _event: MutableLiveData<String> = MutableLiveData()
    val event: LiveData<String> get() = _event

}
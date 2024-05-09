package com.example.nbc_search.presentation.ui.viewpager

import androidx.annotation.StringRes
import com.example.nbc_search.R

enum class TabType(val position: Int, @StringRes val tabName: Int) {
    SEARCH(0, R.string.activity_main_tab_search),
    STORAGE(1, R.string.activity_main_tab_storage);

    companion object {
        fun from(position: Int): TabType {
            return entries.first { it.position == position }
        }
    }
}
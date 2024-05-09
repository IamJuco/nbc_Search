package com.example.nbc_search.presentation.ui.viewpager

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.nbc_search.presentation.ui.search.SearchFragment
import com.example.nbc_search.presentation.ui.storage.StorageFragment

class ViewPager2Adapter(items: FragmentActivity) : FragmentStateAdapter(items) {

    override fun getItemCount(): Int {
        return TabType.entries.size
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            TabType.SEARCH.position -> SearchFragment()
            TabType.STORAGE.position -> StorageFragment()

            else -> throw IllegalArgumentException("ERROR -> 존재하지 않는 탭입니다.")
        }
    }
}
package com.example.nbc_search

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.nbc_search.databinding.ActivityMainBinding
import com.example.nbc_search.presentation.ui.search.SearchFragment
import com.example.nbc_search.presentation.ui.storage.StorageFragment
import com.example.nbc_search.presentation.ui.viewpager.ViewPager2Adapter
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private lateinit var viewPager2Adapter: ViewPager2Adapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setupViewPager2()

    }

    private fun setupViewPager2() {
        viewPager2Adapter = ViewPager2Adapter(this)
        viewPager2Adapter.addFragment(SearchFragment())
        viewPager2Adapter.addFragment(StorageFragment())

        binding.apply {
            vpArea.adapter = viewPager2Adapter
            TabLayoutMediator(tabMain, vpArea) { tab, position ->
                when (position) {
                    0 -> tab.text = getString(R.string.activity_main_tab_search)
                    1 -> tab.text = getString(R.string.activity_main_tab_storage)
                }
            }.attach()
        }

    }
}
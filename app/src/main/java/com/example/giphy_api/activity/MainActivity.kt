package com.example.giphy_api.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.giphy_api.adapter.PagerAdapter
import com.example.giphy_api.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var pagerAdapter: PagerAdapter
    private val tabTextList  = arrayListOf("Trending","Favorites")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        pagerAdapter = PagerAdapter(this)

        binding.apply {
            viewpager.adapter = pagerAdapter

            TabLayoutMediator(tabLayout,viewpager){tab, position ->
                tab.text = tabTextList[position]
            }.attach()
        }


    }
}
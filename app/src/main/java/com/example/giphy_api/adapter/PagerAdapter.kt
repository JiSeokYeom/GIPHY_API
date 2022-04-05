package com.example.giphy_api.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.giphy_api.fragment.FavoritesFragment
import com.example.giphy_api.fragment.TrendingFragment

class PagerAdapter(fragment: FragmentActivity):FragmentStateAdapter(fragment) {
    private val NUM_PAGES = 2
    override fun getItemCount(): Int = NUM_PAGES

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> TrendingFragment()
            else -> FavoritesFragment()
        }
    }
}
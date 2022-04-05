package com.example.giphy_api.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.giphy_api.adapter.TrendingRvAdapter
import com.example.giphy_api.databinding.FragmentFavoritesBinding
import com.example.giphy_api.style.MyItemDecoration
import com.example.giphy_api.viewmodel.FavoritesViewModel

class FavoritesFragment : Fragment(){
    private val trendingViewModel: FavoritesViewModel by lazy {
        ViewModelProvider(this).get(FavoritesViewModel::class.java)
    }
    private lateinit var trendingRvAdapter : TrendingRvAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentFavoritesBinding.inflate(inflater,container,false)

        trendingRvAdapter = TrendingRvAdapter()

        binding.apply {
            favoritesRv.adapter = trendingRvAdapter
            favoritesRv.layoutManager = GridLayoutManager(requireContext(), 3, RecyclerView.VERTICAL, false)
            favoritesRv.addItemDecoration(MyItemDecoration())

            trendingViewModel.userUrls.observe(viewLifecycleOwner){
                 for (urls in it){
                     trendingRvAdapter.favoriteData = listOf(urls)
                 }

            }


        }

        return binding.root
    }
}
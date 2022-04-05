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
import com.example.giphy_api.databinding.FragmentTrendingBinding
import com.example.giphy_api.style.MyItemDecoration
import com.example.giphy_api.viewmodel.TrendingViewModel

class TrendingFragment : Fragment(){
    private val trendingViewModel: TrendingViewModel by lazy {
        ViewModelProvider(this).get(TrendingViewModel::class.java)
    }
    private lateinit var trendingRvAdapter : TrendingRvAdapter
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentTrendingBinding.inflate(inflater,container,false)

        trendingRvAdapter = TrendingRvAdapter()

        binding.apply {
            trendingRv.adapter = trendingRvAdapter
            trendingRv.layoutManager = GridLayoutManager(requireContext(),3,RecyclerView.VERTICAL,false)
            trendingRv.addItemDecoration(MyItemDecoration())

            trendingViewModel.getTrending().observe(viewLifecycleOwner){
                it.data.let { data ->
                    for (url in data){
                        trendingRvAdapter.trendingData.add(url)
                    }
                }
                trendingRvAdapter.notifyDataSetChanged()
            }
        }

        return binding.root
    }
}
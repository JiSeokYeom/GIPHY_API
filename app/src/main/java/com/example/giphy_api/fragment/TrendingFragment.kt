package com.example.giphy_api.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.giphy_api.R
import com.example.giphy_api.adapter.TrendingRvAdapter
import com.example.giphy_api.databinding.FragmentTrendingBinding
import com.example.giphy_api.databinding.RvItemBinding
import com.example.giphy_api.room.entity.UserFavoritesData
import com.example.giphy_api.style.MyItemDecoration
import com.example.giphy_api.viewmodel.TrendingViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TrendingFragment : Fragment(){
    private val trendingViewModel: TrendingViewModel by lazy {
        ViewModelProvider(this).get(TrendingViewModel::class.java)
    }
    private lateinit var trendingRvAdapter : TrendingRvAdapter
    private val TAG = "TrendingFragment"
    var selectSW = true
    companion object{
        var pageOffset : Int = 0
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentTrendingBinding.inflate(inflater,container,false)

        trendingRvAdapter = TrendingRvAdapter()

        binding.apply {
            trendingRv.adapter = trendingRvAdapter
            trendingRv.layoutManager = GridLayoutManager(requireContext(),3,RecyclerView.VERTICAL,false)
            trendingRv.addItemDecoration(MyItemDecoration())

            trendingViewModel.getTrending(pageOffset).observe(viewLifecycleOwner){
                it.data.let { data ->
                    for (url in data){
                        trendingRvAdapter.trendingData.add(url)
                    }
                }
                trendingRvAdapter.notifyDataSetChanged()
            }

            trendingRvAdapter.setItemClickListener(object : TrendingRvAdapter.OnItemClickListener{
                override fun onClick(v: View, binding: RvItemBinding ,position: Int) {
                    Log.d(TAG,"${trendingRvAdapter.trendingData[position].images.fixed_height.url}")
                    Log.d(TAG,"$position")
                    if (selectSW){
                        binding.FavoritesImg.setImageResource(R.drawable.star_pressed)
                        CoroutineScope(Dispatchers.IO).launch {
                            trendingViewModel.insert(UserFavoritesData(trendingRvAdapter.trendingData[position].images.fixed_height.url))
                        }
                        selectSW = false
                    }
                    else{
                        binding.FavoritesImg.setImageResource(R.drawable.star)
                        CoroutineScope(Dispatchers.IO).launch {
                            trendingViewModel.delete(trendingRvAdapter.trendingData[position].images.fixed_height.url)
                        }
                        selectSW = true
                    }
                }
            })

            // 스크롤 끝 확인
            trendingRv.addOnScrollListener(object : RecyclerView.OnScrollListener(){
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)

                    val lastVisibleItemPosition = (recyclerView.layoutManager as LinearLayoutManager?)!!.findLastCompletelyVisibleItemPosition()
                    val itemTotalCount = recyclerView.adapter!!.itemCount-1

                    if (!recyclerView.canScrollVertically(1) && lastVisibleItemPosition == itemTotalCount) {
                        Log.d(TAG,"카운터 $itemTotalCount 포지션 끝 $lastVisibleItemPosition")
                        pageOffset = lastVisibleItemPosition
                        moreItem()
                    }
                }
            })



        }

        return binding.root
    }

    private fun moreItem() {
        trendingViewModel.getTrending(pageOffset).observe(viewLifecycleOwner){
            it.data.let { data ->
                for (url in data){
                    trendingRvAdapter.trendingData.add(url)
                }
            }
            trendingRvAdapter.notifyItemRangeInserted((pageOffset - 1) * 10, 21)
        }
    }
}
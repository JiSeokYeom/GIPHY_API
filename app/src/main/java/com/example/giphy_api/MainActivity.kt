package com.example.giphy_api

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.giphy_api.adapter.MainRvAdapter
import com.example.giphy_api.databinding.ActivityMainBinding
import com.example.giphy_api.model.TrendingDTO
import com.example.giphy_api.style.MyItemDecoration
import com.example.giphy_api.viewmodel.MainViewModel
import com.giphy.sdk.analytics.GiphyPingbacks.context
import com.giphy.sdk.ui.Giphy
import com.giphy.sdk.ui.views.GiphyDialogFragment

class MainActivity : AppCompatActivity() {
    private val mainViewModel: MainViewModel by lazy {
        ViewModelProvider(this).get(MainViewModel::class.java)
    }
    private lateinit var binding: ActivityMainBinding
    private lateinit var mainAdapterRv : MainRvAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mainAdapterRv = MainRvAdapter()
        binding.apply {
            mainRv.adapter = mainAdapterRv
            mainRv.layoutManager = GridLayoutManager(this@MainActivity,3,RecyclerView.VERTICAL,false)
            mainRv.addItemDecoration(MyItemDecoration())

            mainViewModel.getTrending().observe(this@MainActivity){
                it.data.let { data ->
                    for (uri in data){
                        mainAdapterRv.mData.add(uri)
                    }
                }

                mainAdapterRv.notifyDataSetChanged()
            }
            /*mainAdapterRv.mData.add(TrendingDTO("http://www.reddit.com/r/reactiongifs/comments/1xpyaa/mfw_im_trying_to_figure_out_where_that_weed_smell/"))
            mainAdapterRv.mData.add(TrendingDTO("https://t1.daumcdn.net/cfile/tistory/2341B34A58070D4E04"))
            mainAdapterRv.mData.add(TrendingDTO("https://t1.daumcdn.net/cfile/tistory/2341B34A58070D4E04"))
            mainAdapterRv.mData.add(TrendingDTO("https://t1.daumcdn.net/cfile/tistory/2341B34A58070D4E04"))*/


        }

      //  GiphyDialogFragment.newInstance().show(supportFragmentManager,"dialog")
    }
}
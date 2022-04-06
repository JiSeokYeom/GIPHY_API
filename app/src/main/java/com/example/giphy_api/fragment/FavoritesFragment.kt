package com.example.giphy_api.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.giphy_api.adapter.FavoritesRvAdapter
import com.example.giphy_api.adapter.TrendingRvAdapter
import com.example.giphy_api.databinding.FragmentFavoritesBinding
import com.example.giphy_api.style.MyItemDecoration
import com.example.giphy_api.viewmodel.FavoritesViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

class FavoritesFragment : Fragment(){
    private val favoritesViewModel: FavoritesViewModel by lazy {
        ViewModelProvider(this).get(FavoritesViewModel::class.java)
    }
    private lateinit var favoritesRvAdapter: FavoritesRvAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentFavoritesBinding.inflate(inflater,container,false)

        favoritesRvAdapter = FavoritesRvAdapter()

        binding.apply {
            favoritesRv.adapter = favoritesRvAdapter
            favoritesRv.layoutManager = GridLayoutManager(requireContext(), 3, RecyclerView.VERTICAL, false)
            favoritesRv.addItemDecoration(MyItemDecoration())

            favoritesViewModel.userUrls.observe(viewLifecycleOwner){
                favoritesRvAdapter.favoritesData = it
                favoritesRvAdapter.notifyDataSetChanged()
            }

            favoritesRvAdapter.setItemClickListener(object : FavoritesRvAdapter.OnItemClickListener{
                override fun onClick(v: View, position: Int) {
                    CoroutineScope(Dispatchers.IO).launch {
                        favoritesViewModel.delete(favoritesRvAdapter.favoritesData[position].url)
                    }
                }
            })


        }

        return binding.root
    }
}
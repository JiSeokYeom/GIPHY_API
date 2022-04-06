package com.example.giphy_api.adapter

import android.graphics.Color
import android.util.SparseBooleanArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.giphy_api.R
import com.example.giphy_api.databinding.RvItemBinding
import com.example.giphy_api.model.list
import com.example.giphy_api.room.entity.UserFavoritesData
import com.example.giphy_api.viewmodel.TrendingViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TrendingRvAdapter(fragment: Fragment) : RecyclerView.Adapter<TrendingRvAdapter.ViewHolder>(){
    var trendingData = mutableListOf<list>()
    var selectArray = SparseBooleanArray(0)
    var selectSW = false
    private val trendingViewModel: TrendingViewModel by lazy {
        ViewModelProvider(fragment).get(TrendingViewModel::class.java)
    }

    interface OnItemClickListener {
        fun onClick(v: View, binding: RvItemBinding, position: Int)
    }

    fun setItemClickListener(onItemClickListener: OnItemClickListener) {
        this.itemClickListener = onItemClickListener
    }

    private lateinit var itemClickListener : OnItemClickListener

    inner class ViewHolder(var binding : RvItemBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(item:list){
            Glide.with(itemView.context)
                .load(item.images.fixed_height.url)
                .into(binding.itemImg)
            binding.FavoritesImg.setOnClickListener {
                if (selectArray.get(position, false)){
                    selectArray.put(position, false)
                    binding.FavoritesImg.setImageResource(R.drawable.star)
                    CoroutineScope(Dispatchers.IO).launch {
                        trendingViewModel.delete(trendingData[position].images.fixed_height.url)
                    }
                } else {
                    selectArray.put(position, true)
                    binding.FavoritesImg.setImageResource(R.drawable.star_pressed)
                    CoroutineScope(Dispatchers.IO).launch {
                        trendingViewModel.insert(UserFavoritesData(trendingData[position].images.fixed_height.url))
                    }
                }

//                itemClickListener.onClick(it, binding,position)
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = RvItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = trendingData[position]
        if (selectArray.get(position,false)){
            holder.binding.FavoritesImg.setImageResource(R.drawable.star_pressed)
        }
        else{
            holder.binding.FavoritesImg.setImageResource(R.drawable.star)
        }
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return trendingData.size
    }
}

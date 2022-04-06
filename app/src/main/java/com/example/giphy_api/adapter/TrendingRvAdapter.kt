package com.example.giphy_api.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.giphy_api.R
import com.example.giphy_api.databinding.RvItemBinding
import com.example.giphy_api.model.list
import com.example.giphy_api.room.entity.UserFavoritesData

class TrendingRvAdapter : RecyclerView.Adapter<TrendingRvAdapter.ViewHolder>(){
    var trendingData = mutableListOf<list>()

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
                itemClickListener.onClick(it, binding,position)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = RvItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = trendingData[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return trendingData.size
    }
}

package com.example.giphy_api.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.giphy_api.databinding.MainRvItemBinding
import com.example.giphy_api.model.list

class MainRvAdapter : RecyclerView.Adapter<MainRvAdapter.ViewHolder>(){
    var mData = mutableListOf<list>()

    inner class ViewHolder(var binding : MainRvItemBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(item:list){
            Glide.with(itemView.context)
                .load(item.bitly_url)
                .into(binding.itemImg)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = MainRvItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = mData[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return mData.size
    }
}

package com.gram15inch.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gram15inch.mycoupangeats.databinding.LayoutPagerTopBannerBinding


class BannerAdapter(val item: List<Int>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return BannerPagerViewHolder(
            LayoutPagerTopBannerBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = 10000

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val pos = position % item.size
        (holder as BannerPagerViewHolder).bind(item[pos])
    }

    inner class BannerPagerViewHolder(var binding: LayoutPagerTopBannerBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(res: Int) {
            binding.apply {
                topBnImg.setImageResource(res)
            }
        }
    }

}
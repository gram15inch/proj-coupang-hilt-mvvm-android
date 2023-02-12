package com.gram15inch.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.gram15inch.mycoupangeats.R
import com.gram15inch.mycoupangeats.databinding.LayoutPagerMenuBannerBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MenuBannerAdapter(val item: List<String>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var indicator = 1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return BannerPagerViewHolder(
            LayoutPagerMenuBannerBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = 10000

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (item.isNotEmpty()) {
            val pos = position % item.size
            indicator = pos
            (holder as BannerPagerViewHolder).bind(item[pos])
        }
    }

    inner class BannerPagerViewHolder(var binding: LayoutPagerMenuBannerBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(res: String) {
                CoroutineScope(Dispatchers.Main).launch {
                    Glide.with(binding.root)
                        .load(res)
                        .error(R.drawable.bn_menu_photo)
                        .into(binding.menuHolderBanner)
                }
        }
    }

}
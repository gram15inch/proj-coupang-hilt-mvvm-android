package com.gram15inch.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.gram15inch.domain.model.store.home.HomeCategory
import com.gram15inch.mycoupangeats.R
import com.gram15inch.mycoupangeats.databinding.LayoutHolderHomeCategoryBinding

class HomeCategoryAdapter(onclick: (Int) -> Unit) :
    ListAdapter<HomeCategory, HomeCategoryAdapter.HomeCategoryViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeCategoryViewHolder {
        return HomeCategoryViewHolder(
            LayoutHolderHomeCategoryBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: HomeCategoryViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current)
    }

    override fun getItemCount(): Int {
        return this.currentList.size
    }


    inner class HomeCategoryViewHolder(var binding: LayoutHolderHomeCategoryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(res: HomeCategory) {
            binding.homeCategoryNameTxt.text = res.name

            Glide.with(binding.root).load(res.url)
                .error(R.color.white)
                .into(binding.homeCategoryImg)

        }
    }


    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<HomeCategory>() {
            override fun areItemsTheSame(oldItem: HomeCategory, newItem: HomeCategory): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: HomeCategory, newItem: HomeCategory): Boolean {
                return oldItem == newItem

            }
        }
    }
}
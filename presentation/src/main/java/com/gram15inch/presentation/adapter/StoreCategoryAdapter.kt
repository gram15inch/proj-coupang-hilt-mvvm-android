package com.gram15inch.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.gram15inch.domain.model.store.detail.StoreCategory
import com.gram15inch.domain.model.store.detail.StoreCategoryMenu
import com.gram15inch.mycoupangeats.databinding.LayoutHolderStoreCategoryBinding

class StoreCategoryAdapter(private val onItemClicked: (StoreCategoryMenu) -> Unit) :
    ListAdapter<StoreCategory,RecyclerView.ViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return StoreCategoryViewHolder(
            LayoutHolderStoreCategoryBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = currentList.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val pos = position % currentList.size
        val current = getItem(pos)
        (holder as StoreCategoryViewHolder).bind(current)
        if (currentList.size ==position+1)
            holder.binding.menuCtgrBtLine.visibility = View.INVISIBLE
    }

    inner class StoreCategoryViewHolder(var binding: LayoutHolderStoreCategoryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(res: StoreCategory) {
            binding.apply {
                binding.menuCtgrName.text = res.title
                binding.menuCtgrInfo.text = res.decrip
                binding.menuRcy.adapter = StoreCategoryMenuAdapter(onItemClicked)
                    .apply { this.submitList(res.menuList) }
            }
        }
    }

    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<StoreCategory>() {
            override fun areItemsTheSame(oldItem: StoreCategory, newItem: StoreCategory): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: StoreCategory, newItem: StoreCategory): Boolean {
                return oldItem == newItem

            }
        }
    }
}
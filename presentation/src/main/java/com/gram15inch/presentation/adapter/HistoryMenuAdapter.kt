package com.gram15inch.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.clone.mycoupang.domain.model.order.history.HistoryMenu
import com.gram15inch.mycoupangeats.databinding.LayoutHolderHistoryMenuBinding

class HistoryMenuAdapter :
    ListAdapter<HistoryMenu, HistoryMenuAdapter.HistoryMenuViewHolder>(DiffCallback) {
    var menuIdx= 1
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryMenuViewHolder {
        return HistoryMenuViewHolder(
            LayoutHolderHistoryMenuBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: HistoryMenuViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current)
    }

    override fun getItemCount(): Int {
        return this.currentList.size
    }

    inner class HistoryMenuViewHolder(var binding: LayoutHolderHistoryMenuBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(res: HistoryMenu) {
            binding.holderHistoryMenuNoTxt.text = (adapterPosition+1).toString()
            binding.holderHistoryMenuNameTxt.text = res.menuName
            if(res.optionName.isNotEmpty())
                binding.holderHistoryOptionNameTxt.text = res.optionName.substring(0,res.optionName.length-1)
        }
    }


    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<HistoryMenu>() {
            override fun areItemsTheSame(oldItem: HistoryMenu, newItem: HistoryMenu): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: HistoryMenu, newItem: HistoryMenu): Boolean {
                return oldItem == newItem

            }
        }
    }
}
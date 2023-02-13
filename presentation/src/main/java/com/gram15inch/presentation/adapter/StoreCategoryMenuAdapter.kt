package com.gram15inch.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.gram15inch.domain.model.store.StoreCategoryMenu
import com.gram15inch.mycoupangeats.databinding.LayoutHolderStoreCategoryMenuBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.DecimalFormat


class StoreCategoryMenuAdapter(val onclick: (StoreCategoryMenu)->Unit) :
    ListAdapter<StoreCategoryMenu, RecyclerView.ViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return MenuViewHolder(
            LayoutHolderStoreCategoryMenuBinding.inflate(
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
        (holder as MenuViewHolder).bind(current)
        if(currentList.size == position+1)
            holder.binding.menuBtLine.visibility = View.INVISIBLE

    }

    inner class MenuViewHolder(var binding: LayoutHolderStoreCategoryMenuBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(res: StoreCategoryMenu) {
            binding.apply {

                CoroutineScope(Dispatchers.Main).launch {
                    Glide.with(binding.root).load(res.img).into(menuTitleImg)
                }
                menuNameTxt.text = res.name
                menuInfoiTxt.text = res.descrip
                menuPriceTxt.text = DecimalFormat("#,###원").format(res.price)

                menuTitleImg.setOnClickListener {
                    onclick.invoke(res)
                }
                menuOrderMostLabel.visibility = if(res.orderMost) View.VISIBLE else View.GONE
                menuReivewMostLabel.visibility = if(res.reviewMost) View.VISIBLE else View.GONE
                menuLikeTxt.text = res.like.toString()
            }
        }
    }

    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<StoreCategoryMenu>() {
            override fun areItemsTheSame(oldItem: StoreCategoryMenu, newItem: StoreCategoryMenu): Boolean {
                return oldItem.name == newItem.name
            }

            override fun areContentsTheSame(oldItem: StoreCategoryMenu, newItem: StoreCategoryMenu): Boolean {
                return oldItem == newItem

            }
        }
    }
}
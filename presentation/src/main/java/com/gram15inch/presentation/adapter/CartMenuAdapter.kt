package com.gram15inch.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.gram15inch.domain.model.store.CartMenu
import com.clone.mycoupang.domain.policy.calculateCartPrice
import com.gram15inch.mycoupangeats.databinding.LayoutHolderCartMenuBinding
import java.text.DecimalFormat

class CartMenuAdapter :
    ListAdapter<CartMenu, CartMenuAdapter.CartMenuViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartMenuViewHolder {
        return CartMenuViewHolder(
            LayoutHolderCartMenuBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: CartMenuViewHolder, position: Int) {
        val current = getItem(position)
        holder.itemView.setOnClickListener {

        }
        holder.bind(current)
    }

    override fun getItemCount(): Int {
        return this.currentList.size
    }


    private fun getOptionsString(cartMenu: CartMenu): String {
        var options = ""

        cartMenu.typeList.forEach {
            it.optList.forEach {
                options += "${it.Name}, "
            }
        }
        if (options.isNotEmpty())
            options.substring(0, options.length - 2)
        return options
    }

    inner class CartMenuViewHolder(var binding: LayoutHolderCartMenuBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(res: CartMenu) {
            binding.apply {
                cartMenuNameTxt.text = res.menu.name
                if(res.typeList.isNotEmpty())
                    cartMenuDetailTxt.text = getOptionsString(res)
                else
                    cartMenuDetailTxt.visibility = View.GONE
                cartMenuPriceTxt.text =
                    DecimalFormat("#,###원").format(calculateCartPrice(listOf(res)))
                cartMenuCountSpnTxt.text = res.count.toString()
            }
        }
    }


    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<CartMenu>() {
            override fun areItemsTheSame(oldItem: CartMenu, newItem: CartMenu): Boolean {
                return oldItem.menuId == newItem.menuId
            }

            override fun areContentsTheSame(oldItem: CartMenu, newItem: CartMenu): Boolean {
                return oldItem == newItem

            }
        }
    }
}
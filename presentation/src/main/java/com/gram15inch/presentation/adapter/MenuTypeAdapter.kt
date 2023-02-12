package com.gram15inch.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.gram15inch.domain.model.menu.MenuType
import com.gram15inch.domain.model.menu.SelectedMenuType
import com.gram15inch.presentation.ui.MenuActivity
import com.gram15inch.mycoupangeats.databinding.LayoutHolderMenuTypeBinding

class MenuTypeAdapter(
    val activity: MenuActivity,
    private val onItemClicked: (MenuType) -> Unit
) :
    ListAdapter<MenuType, MenuTypeAdapter.MenuTypeViewHolder>(DiffCallback) {
    val selectedTypes = mutableListOf<SelectedMenuType>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuTypeViewHolder {
        return MenuTypeViewHolder(
            LayoutHolderMenuTypeBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MenuTypeViewHolder, position: Int) {
        val current = getItem(position)

        holder.itemView.setOnClickListener {
            onItemClicked(current)
        }
        holder.bind(current)
    }

    override fun getItemCount(): Int {
        return this.currentList.size
    }

    inner class MenuTypeViewHolder(var binding: LayoutHolderMenuTypeBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(res: MenuType) {
            binding.apply {
                binding.menuCtgrName.text = res.Name
                binding.menuCtgrOpRcy.adapter =
                    MenuTypeOptionAdapter(
                        activity,
                        res.optList,
                        res.necessary
                    ) { onItemClicked.invoke(res) }.apply {
                        selectedTypes.add(
                            SelectedMenuType(
                                res.id,
                                res.Name,
                                res.multiple,
                                res.necessary,
                                this.selectedOption
                            )
                        )
                    }

                binding.menuCtgrInfo.visibility = if (res.necessary) View.VISIBLE else View.GONE
            }
        }
    }

    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<MenuType>() {
            override fun areItemsTheSame(oldItem: MenuType, newItem: MenuType): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: MenuType, newItem: MenuType): Boolean {
                return oldItem == newItem

            }
        }
    }

}
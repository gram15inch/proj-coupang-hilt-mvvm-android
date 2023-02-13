package com.gram15inch.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.GlideException
import com.gram15inch.domain.model.store.detail.Store
import com.gram15inch.mycoupangeats.R
import com.gram15inch.mycoupangeats.databinding.LayoutHolderHomeStoreBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.FileNotFoundException

class HomeStoreAdapter (private val onItemClicked: (Store) -> Unit) :
    ListAdapter<Store, HomeStoreAdapter.StoreHolder>(DiffCallback) {
   inner class StoreHolder(var binding: LayoutHolderHomeStoreBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(store: Store) {
            binding.apply {
                listOf(storeMainImg,
                        storeSubUpImg,
                        storeSubDownImg)
                    .also {

                        for((i,imgView) in it.withIndex())
                            CoroutineScope(Dispatchers.Main).launch{
                                try {
                                    Glide.with(binding.root)
                                        .load(store.imgs[i])
                                        .error(R.drawable.bn_menu_photo)
                                        .into(imgView)
                                }catch (e: FileNotFoundException){
                                }catch (e: GlideException){

                                }
                            }


                    }
                storeNameTxt.text = store.storeName
                storeDevTimeTxt.text = "${store.deliveryTime} 분"
                val feeStr= if (store.fee==0) "무료배달" else "배달비 ${store.fee}원~"
                storeInfoTxt.text = "${store.star}(${store.reviewCount}) ⸳ ${store.distance}km ⸳ "+feeStr
                storeIsPacLabel.visibility = if(store.isPackage) View.VISIBLE else View.GONE
                storeIsCiLabel.visibility = if(store.isCi) View.VISIBLE else View.GONE
                storeNewLabel.visibility = if(store.isNew) View.VISIBLE else View.GONE
                storeCouponLabel.visibility = if(store.coupon ==0) View.GONE else View.VISIBLE
                storeCouponLabel.text = " ${store.coupon} 포장쿠폰"
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoreHolder {
        return StoreHolder(
            LayoutHolderHomeStoreBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: StoreHolder, position: Int) {
        val current = getItem(position)

        holder.itemView.setOnClickListener {
            onItemClicked(current)
        }
        holder.bind(current)
    }

    override fun getItemCount(): Int {
        return this.currentList.size
    }
    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<Store>() {
            override fun areItemsTheSame(oldItem: Store, newItem: Store): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Store, newItem: Store): Boolean {
                return oldItem == newItem

            }
        }
    }
}
package com.gram15inch.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gram15inch.mycoupangeats.databinding.LayoutHolderDialogChoiceBinding

class BottomChoiceAdapter(val item:List<String>,val onclick :(String)->Unit) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return BottomChoiceHolder(
            LayoutHolderDialogChoiceBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = item.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

            (holder as BottomChoiceHolder).bind(item[position])
    }

    inner class BottomChoiceHolder(var binding: LayoutHolderDialogChoiceBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(res: String) {
            binding.msg = res
            binding.choiceTxt.setOnClickListener {
                onclick.invoke(res)
            }
        }
    }

}
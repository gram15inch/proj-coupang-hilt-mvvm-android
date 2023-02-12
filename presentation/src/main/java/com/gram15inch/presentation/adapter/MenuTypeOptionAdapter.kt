package com.gram15inch.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

import com.gram15inch.domain.model.menu.MenuTypeOption
import com.gram15inch.presentation.ui.MenuActivity
import com.gram15inch.mycoupangeats.databinding.LayoutHolderMenuTypeOptionMultipleBinding
import com.gram15inch.mycoupangeats.databinding.LayoutHolderMenuTypeOptionNecessarayBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import java.text.DecimalFormat


class MenuTypeOptionAdapter(
    private val activity: MenuActivity,
    private val item: List<MenuTypeOption>,
    private val isNecessary: Boolean,
    private val onItemClicked: () -> Unit
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    val optionState = mutableMapOf<Int, MutableStateFlow<Boolean>>()
    val selectedOption = MutableStateFlow<List<MenuTypeOption>>(emptyList())
    private val _selectedOption = mutableSetOf<MenuTypeOption>()
    init {

        CoroutineScope(Dispatchers.IO).launch {
            selectedOption.collect() {
                Timber.tag("adapter").d("=====================================")
                Timber.tag("adapter").d("size: ${it.size} \n ${it}")
                activity.viewModel.calculate()
            }
        }
        item.forEach {
            optionState[it.id] = MutableStateFlow(false)
            optionState.forEach { entryRef ->
                CoroutineScope(Dispatchers.IO).launch {
                    entryRef.value.collect() { isChecked ->
                        val currentItem = item.firstOrNull() { cl -> cl.id == entryRef.key }
                        if (isChecked) {
                            currentItem
                                .also { option ->
                                    if (option != null)
                                        _selectedOption
                                            .add(option)
                                            .also { isAdd ->
                                                if (isAdd) {
                                                    selectedOption.emit(_selectedOption.toList())
                                                    //Timber.tag("adapter").d("add: ${it}")
                                                }
                                            }
                                }

                            //Timber.tag("typeTest").d("selected: ${selectedOption.value.first()}")
                            if (isNecessary) {
                                optionState.forEach { entry ->
                                    if (entryRef.key != entry.key) {
                                        entry.value.emit(false)
                                    }
                                }
                            }

                        } else {
                            currentItem
                                .also { option ->
                                    _selectedOption
                                        .remove(option)
                                        .also { isRemove ->
                                            if (isRemove) {
                                                selectedOption.emit(_selectedOption.toList())
                                                //Timber.tag("adapter").d("remove: ${it}")
                                            }
                                        }
                                }
                        }
                    }
                }
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        if (isNecessary)
            return MenuTypeOptionNecessaryViewHolder(
                LayoutHolderMenuTypeOptionNecessarayBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        else
            return MenuTypeOptionMultipleViewHolder(
                LayoutHolderMenuTypeOptionMultipleBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
    }

    override fun getItemCount(): Int = item.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val pos = position % item.size

        if (isNecessary)
            (holder as MenuTypeOptionNecessaryViewHolder).bind(item[pos])
        else
            (holder as MenuTypeOptionMultipleViewHolder).bind(item[pos])

    }

    inner class MenuTypeOptionNecessaryViewHolder(var binding: LayoutHolderMenuTypeOptionNecessarayBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(res: MenuTypeOption) {
            binding.valueId = res.id
            binding.menuOpNameTxt.text = res.Name
            binding.menuOpPriceTxt.text = "(+${DecimalFormat("#,###원").format(res.price)})"
            binding.adapter = this@MenuTypeOptionAdapter
            binding.lifecycleOwner = activity
            activity.viewModel
        }
    }

    inner class MenuTypeOptionMultipleViewHolder(var binding: LayoutHolderMenuTypeOptionMultipleBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(res: MenuTypeOption) {
            binding.valueId = res.id
            binding.menuOpNameTxt.text = res.Name
            binding.menuOpPriceTxt.text = "(+${DecimalFormat("#,###원").format(res.price)})"
            binding.adapter = this@MenuTypeOptionAdapter
            binding.lifecycleOwner = activity
        }
    }


}
package com.gram15inch.presentation.ui.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.gram15inch.mycoupangeats.R
import com.gram15inch.mycoupangeats.databinding.FragmentChoiceBottomDialogBinding
import com.gram15inch.presentation.adapter.BottomChoiceAdapter

class ChoiceBottomDialogFragment(val item:List<String>, val onclick:(String)->Unit) : BottomSheetDialogFragment() {
    var _binding : FragmentChoiceBottomDialogBinding? = null
    val binding get()=_binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_choice_bottom_dialog, container, false)

        setBtn()
        setRcy()
        return binding.root
    }

    private fun setRcy() {
        binding.dialogBtChoiceRcy.adapter = BottomChoiceAdapter(item){
            onclick.invoke(it)
            this.dismiss()
        }
    }

    fun setBtn(){
        binding.dialogBtExitBtn.setOnClickListener {
           this.dismiss()
        }
    }

}
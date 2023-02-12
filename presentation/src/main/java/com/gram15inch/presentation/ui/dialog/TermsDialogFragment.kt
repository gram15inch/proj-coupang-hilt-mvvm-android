package com.gram15inch.presentation.ui.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment

import com.clone.mycoupang.domain.model.SignUpTerms
import com.gram15inch.mycoupangeats.R
import com.gram15inch.mycoupangeats.databinding.FragmentTermsDialogBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class TermsDialogFragment(val terms: SignUpTerms) : DialogFragment() {
    private var _binding : FragmentTermsDialogBinding? = null
    val binding get()=_binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_terms_dialog, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner =this
        binding.fragment = this
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }





}
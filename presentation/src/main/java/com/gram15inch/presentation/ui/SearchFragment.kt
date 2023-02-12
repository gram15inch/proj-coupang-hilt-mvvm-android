package com.gram15inch.presentation.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.gram15inch.presentation.viewmodel.SearchViewModel
import com.gram15inch.mycoupangeats.R
import com.gram15inch.mycoupangeats.databinding.FragmentSearchBinding
import com.gram15inch.presentation.base.DataBindingFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : DataBindingFragment<FragmentSearchBinding>(R.layout.fragment_search) {
    val viewModel: SearchViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
    }
}
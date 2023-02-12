package com.gram15inch.presentation.viewmodel

import com.gram15inch.presentation.base.ErrorHandleViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(): ErrorHandleViewModel() {
        var count =0
    init {
        Timber.tag("viewModel").d("main viewModel create!!!!")
    }
}
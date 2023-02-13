package com.gram15inch.presentation.viewmodel

import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.viewpager2.widget.ViewPager2
import com.gram15inch.presentation.base.ErrorHandleViewModel
import com.gram15inch.domain.model.cart.CartMenu
import com.gram15inch.domain.model.store.StoreDetail
import com.gram15inch.domain.repository.CartRepository
import com.gram15inch.domain.repository.StoreRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class StoreViewModel @Inject constructor(
    private val storeRepository: StoreRepository,
    private val cartRepository: CartRepository
) :
    ErrorHandleViewModel() {
    private val _cartMenusFlow = MutableStateFlow<List<CartMenu>>(emptyList())
    val cartMenusLive get() =  _cartMenusFlow.asLiveData()
    val storeFlow = MutableStateFlow<StoreDetail?>(null)
    private lateinit var bannerJob: Job
    var bannerPos = MutableStateFlow(1)


    fun refreshCart() {
        viewModelScope.launch {
            _cartMenusFlow.emit(cartRepository.cartMenus.toList())// ????????????????????
            Timber.tag("currentCart").d("${_cartMenusFlow.value.map { it.menu }}")
        }
    }

    fun refreshStore(storeId:Int) {
        viewModelScope.launch() {
            storeRepository.getStoreDetail(storeId)
                .apply {
                    if(this!=null)
                        storeFlow.emit(this)
                }
        }
    }

    fun startScroll() {
        bannerJob = viewModelScope.launch {
            while (true) {
                delay(1000)
                bannerPos.emit(bannerPos.value + 1)
            }
        }
    }

    fun stopScroll() {
        bannerJob.cancel()
    }

    fun addCart() {
        storeFlow.value.also{
            if(it!=null)
                cartRepository.cartStore.add(it)
        }
    }

    val scrollListener = object : ViewPager2.OnPageChangeCallback() {
        override fun onPageScrolled(
            position: Int,
            positionOffset: Float,
            positionOffsetPixels: Int
        ) {
            super.onPageScrolled(position, positionOffset, positionOffsetPixels)
            if (positionOffsetPixels == 0) {
                viewModelScope.launch {
                    bannerPos.emit(position)
                }
            }
        }

        override fun onPageSelected(position: Int) {
            super.onPageSelected(position)
            //mIndicator.animatePageSelected(position % num_page)
        }
    }

}
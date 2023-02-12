package com.gram15inch.presentation.viewmodel

import androidx.lifecycle.viewModelScope
import com.gram15inch.presentation.base.ErrorHandleViewModel
import com.gram15inch.data.converter.MenuConverter
import com.gram15inch.domain.model.menu.MenuDetail
import com.gram15inch.domain.model.menu.SelectedMenuType
import com.clone.mycoupang.domain.model.store.CartMenu
import com.clone.mycoupang.domain.policy.calculateCartMenuPrice
import com.gram15inch.domain.repository.CartRepository
import com.gram15inch.domain.repository.MenuRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MenuViewModel @Inject constructor(
    private val menuRepository: MenuRepository,
    private val cartRepository: CartRepository
) :
    ErrorHandleViewModel() {
    lateinit var selectedTypes: MutableList<SelectedMenuType>
    val menuFlow = MutableStateFlow<MenuDetail?>(null)
    val menuCount = MutableStateFlow(1)
    val totalPrice = MutableStateFlow(0)


    fun bindSelectedTypes() {
        calculate()
    }

    fun menuRefresh(storeId: Int, menuId: Int) {
        viewModelScope.launch {
            menuRepository.getMenu(storeId, menuId).apply {
                if (this.body()?.isSuccess == true)
                    if (this.body()?.result != null)
                        this.body()?.result?.run {
                            MenuConverter.toMenuDetail(this)
                                .also { menuDetail ->
                                    menuFlow.emit(menuDetail)
                                    totalPrice.emit(menuDetail.price)
                                }
                        }
            }
        }
    }

    fun calculate() {
        viewModelScope.launch {
            menuFlow.value.also { menuDetail ->
                if (menuDetail != null)
                    calculateCartMenuPrice(
                        menuDetail.price,
                        menuCount.value,
                        selectedTypes.map {
                            MenuConverter.toMenuType(it)
                        })
                        .also { calPrice -> totalPrice.emit(calPrice)}
            }
        }
    }

    fun countUp() {
        viewModelScope.launch {
            if (menuCount.value <= 100) {
                menuCount.emit(menuCount.value + 1)
                calculate()
            }
        }
    }

    fun countDown() {
        viewModelScope.launch {
            if (menuCount.value > 1) {
                menuCount.emit(menuCount.value - 1)
                calculate()
            }
        }
    }

    fun addMenu() {
        viewModelScope.launch {
            menuFlow.value.also { menu ->
                if (menu != null)
                    cartRepository.cartMenus.add(
                        CartMenu(
                            menu.id,
                            menu,
                            menuCount.value,
                            selectedTypes.map { MenuConverter.toMenuType(it) }
                        )
                    )
            }
        }
    }
}
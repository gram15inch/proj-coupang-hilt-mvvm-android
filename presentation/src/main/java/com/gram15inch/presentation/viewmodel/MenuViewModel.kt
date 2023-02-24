package com.gram15inch.presentation.viewmodel

import androidx.lifecycle.viewModelScope
import com.gram15inch.presentation.base.ErrorHandleViewModel
import com.gram15inch.domain.model.menu.MenuDetail
import com.gram15inch.domain.model.menu.SelectedMenuType
import com.gram15inch.domain.model.cart.CartMenu
import com.clone.mycoupang.domain.policy.calculateCartMenuPrice
import com.gram15inch.domain.converter.MenuConverter
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
        viewModelScope.launch(exceptionHandler) {
            menuRepository.getMenu(storeId, menuId).apply {
                if(this!=null) {
                    menuFlow.emit(this)
                    totalPrice.emit(this.price)
                }
            }
        }
    }

    fun calculate() {
        viewModelScope.launch(exceptionHandler) {
            menuFlow.value.also { menuDetail ->
                if (menuDetail != null)
                    calculateCartMenuPrice(
                        menuDetail.price,
                        menuCount.value,
                        selectedTypes.map {
                            MenuConverter.toMenuType(it)
                        })
                        .also { calPrice -> totalPrice.emit(calPrice) }
            }
        }
    }

    fun countUp() {
        viewModelScope.launch(exceptionHandler) {
            if (menuCount.value <= 100) {
                menuCount.emit(menuCount.value + 1)
                calculate()
            }
        }
    }

    fun countDown() {
        viewModelScope.launch(exceptionHandler) {
            if (menuCount.value > 1) {
                menuCount.emit(menuCount.value - 1)
                calculate()
            }
        }
    }

    fun addMenu() {
        viewModelScope.launch(exceptionHandler) {
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
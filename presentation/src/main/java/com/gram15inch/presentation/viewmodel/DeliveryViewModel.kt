package com.gram15inch.presentation.viewmodel

import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.gram15inch.domain.model.order.history.History
import com.gram15inch.domain.model.order.history.HistoryMenu
import com.gram15inch.domain.repository.OrderRepository
import com.gram15inch.presentation.base.ErrorHandleViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DeliveryViewModel @Inject constructor(private val orderRepository: OrderRepository) :
    ErrorHandleViewModel() {
    private val _history = MutableStateFlow<List<History>>(emptyList())
    val history = _history.asLiveData()
    private val _historyMenu = MutableStateFlow<List<HistoryMenu>>(emptyList())
    val historyMenu = _historyMenu.asLiveData()

    init {

        setHistoryMenu()
    }

    private fun setHistoryMenu() {
        viewModelScope.launch {
            _history.collect() {
                if (it.isNotEmpty())
                    _historyMenu.emit(it.first().menuList)
            }
        }
    }

    fun refreshHistory(orderId:Int) {
        viewModelScope.launch {// todo userId pref 에서 받아와야함
            orderRepository.getHistory(1).apply {
                this.filter {
                    it.id == orderId
                }.also {
                    _history.emit(it)
                }
            }
        }
    }

}
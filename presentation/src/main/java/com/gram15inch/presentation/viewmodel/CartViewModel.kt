package com.gram15inch.presentation.viewmodel

import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.gram15inch.presentation.base.ErrorHandleViewModel
import com.clone.mycoupang.data.remote.model.order.add.RemoteOrderAdd
import com.clone.mycoupang.data.remote.model.order.add.request.Menu
import com.clone.mycoupang.data.remote.model.order.add.request.OrderAddRequest
import com.gram15inch.data.converter.AddressConverter
import com.gram15inch.data.converter.PayConverter
import com.gram15inch.domain.model.address.Address
import com.clone.mycoupang.domain.model.pay.Pay
import com.clone.mycoupang.domain.model.store.CartMenu
import com.clone.mycoupang.domain.model.store.StoreDetail
import com.clone.mycoupang.domain.policy.calculateCartPrice
import com.gram15inch.domain.repository.AddressRepository
import com.gram15inch.domain.repository.CartRepository
import com.clone.mycoupang.domain.repository.OrderRepository
import com.gram15inch.domain.repository.PayRepository
import com.gram15inch.presentation.MyCoupangEatsApplication
import com.gram15inch.presentation.MyCoupangEatsApplication.Companion.X_ACCESS_TOKEN
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    private val cartRepository: CartRepository,
    private val addressRepository: AddressRepository,
    private val orderRepository: OrderRepository,
    private val payRepository: PayRepository
) :
    ErrorHandleViewModel() {
    private val _cartMenus = MutableStateFlow<List<CartMenu>>(emptyList())
    private val _cartStore = MutableStateFlow<List<StoreDetail>>(emptyList())
    private val _totalPrice = MutableStateFlow(0)
    private val _cartAddr = MutableStateFlow<List<Address>>(emptyList())
    private val _cartOrderAdd = MutableStateFlow<List<RemoteOrderAdd>>(emptyList())
    private val _cartPay = MutableStateFlow<List<Pay>>(emptyList())
    private val _cartDevrMsg = MutableStateFlow( "")
    val cartDevrMsg = _cartDevrMsg.asLiveData()


    val cartMenus = _cartMenus.asLiveData()
    val cartStore = _cartStore.asLiveData()
    val totalPrice = _totalPrice.asLiveData()
    val cartAddr = _cartAddr.asLiveData()
    val cartOrderAdd = _cartOrderAdd.asLiveData()
    val cartPay = _cartPay.asLiveData()

    init {
        refreshCartMenu()
        refreshStore()
        refreshAddr()
        refreshPay()
        setDevrMasg()
    }

    private fun setDevrMasg() {
        setDevrMsgs(cartRepository.devrMsgs.first())
    }

    private fun refreshPay() {
        viewModelScope.launch{
            MyCoupangEatsApplication.prefs.setString(
                X_ACCESS_TOKEN,
                "eyJ0eXBlIjoiand0IiwiYWxnIjoiSFMyNTYifQ.eyJ1c2VySWR4IjoxLCJpYXQiOjE2NzU1ODI0NjYsImV4cCI6MTY3NzA1MzY5NX0.SgGJotjuT18Y-OxNAdSkHpSu1TSAIfW0Oerobh2Seco"
            )     //todo 합칠때 토큰  지우기 (해피)
            payRepository.getPayments().apply {
                if (this.body()?.isSuccess == true)
                    body()?.result?.run {
                        this.run {
                             PayConverter.toPay(this)
                        }.also {
                            _cartPay.emit(it)
                        }
                    }
            }
        }

    }


    fun addOrder() {



        viewModelScope.launch {
            MyCoupangEatsApplication.prefs.setString(
                X_ACCESS_TOKEN,
                "eyJ0eXBlIjoiand0IiwiYWxnIjoiSFMyNTYifQ.eyJ1c2VySWR4IjoxLCJpYXQiOjE2NzU1ODI0NjYsImV4cCI6MTY3NzA1MzY5NX0.SgGJotjuT18Y-OxNAdSkHpSu1TSAIfW0Oerobh2Seco"
            )     //todo 합칠때 토큰  지우기 (해피)
            orderRepository.postOrderAdd(
                _cartStore.value.first().id,
                OrderAddRequest(
                    _cartAddr.value.first().id,
                    0, // todo 일회용품 연결후 교체
                    _cartMenus.value.map { toAddRequestMenu(it) },
                    "111122",
                    _cartPay.value?.firstOrNull(){ it.type=="CREDIT"}?.id?:1,
                    "CREDIT",
                    _cartDevrMsg.value,
                    "가게 요청 텍스트",
                    _totalPrice.value
                )
            ).also {
                viewModelScope.launch {
                    if (it.body()?.isSuccess == true)
                        it.body()?.result?.run {
                            this.run {
                                _cartOrderAdd.emit(listOf(this))
                            }
                        }
                }
            }
        }

    }

    fun toAddRequestMenu(cartMenu: CartMenu): Menu {
        var opListStr = ""
        val opList = cartMenu.typeList.flatMap { it.optList }
        if (opList.isEmpty())
            opListStr = ","
        else
            opList.forEach {
                opListStr += "${it.id},"
            }
        return Menu(cartMenu.count, cartMenu.menuId, opListStr)
    }

    private fun refreshAddr() {
        MyCoupangEatsApplication.prefs.setString(
            X_ACCESS_TOKEN,
            "eyJ0eXBlIjoiand0IiwiYWxnIjoiSFMyNTYifQ.eyJ1c2VySWR4IjoxLCJpYXQiOjE2NzU1MjE0MDgsImV4cCI6MTY3Njk5MjYzN30.cDKAWGYLhki019hj3bKISJ9Lw5unlCzwqqLbnMGp9gs"
        )
        //todo 합칠때 토큰  지우기
        viewModelScope.launch {
            addressRepository.getAddress().apply {
                if (this.body()?.isSuccess == true)
                    body()?.result?.run {
                        this.run {
                            this.map { AddressConverter.toAddress(it) }
                        }.also {
                            _cartAddr.emit(it)
                        }
                    }
            }
        }
    }

    fun refreshCartMenu() {
        viewModelScope.launch {
            _cartMenus.emit(cartRepository.cartMenus)
            _totalPrice.emit(_totalPrice.value + calculateCartPrice(cartRepository.cartMenus))
        }
    }

    fun refreshStore() {
        viewModelScope.launch() {
            _cartStore.emit(cartRepository.cartStore)
            val minfee = _cartStore.value.firstOrNull()?.minFee?:0
            _totalPrice.emit(_totalPrice.value + minfee)
        }
    }

    fun clearCart() {
        cartRepository.cartMenus.clear()
        cartRepository.cartStore.clear()
    }


    fun getDevrMsgs():List<String>{
        return cartRepository.devrMsgs
    }
    fun setDevrMsgs(msg:String){
        viewModelScope.launch {
             _cartDevrMsg.emit(msg)
        }
    }
    private fun noUse() {
        viewModelScope.launch { // 옵션리스트 변환 테스트용
            _cartMenus.collect() {
                it.forEach {
                    Timber.tag("parseTest").d("parse : ${toAddRequestMenu(it)}")
                }
            }
        }
    }
}
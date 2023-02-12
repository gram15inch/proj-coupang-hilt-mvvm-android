package com.clone.mycoupang.domain.model.order.history

import com.clone.mycoupang.domain.model.store.CartMenu

data class History(
    val id: Int,
    val storeName: String,
    val orderDate: String,
    val totalPrice:Int,
    val storeMsg: String,
    val devrMsg: String,
    val devrAddr: String,
    val menuList: List<HistoryMenu>,
)

data class HistoryMenu(val id: Int, val menuName: String,val optionName: String, val count: Int)

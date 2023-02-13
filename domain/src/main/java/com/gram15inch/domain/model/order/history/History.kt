package com.gram15inch.domain.model.order.history

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

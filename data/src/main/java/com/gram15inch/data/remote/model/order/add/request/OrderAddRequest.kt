package com.clone.mycoupang.data.remote.model.order.add.request

data class OrderAddRequest(
    val addressId: Int,
    val disposable: Int,
    val menuList: List<Menu>,
    val password: String,
    val paymentId: Int,
    val payments: String,
    val riderRequest: String,
    val storeRequest: String,
    val totalPrice: Int
)
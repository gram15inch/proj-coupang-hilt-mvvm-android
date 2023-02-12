package com.clone.mycoupang.data.remote.model.order.history

data class OrderInfo(
    val address: String,
    val createdAt: String,
    val disposable: Boolean,
    val orderId: Int,
    val payment: String,
    val riderRequest: String,
    val storeName: String,
    val storeRequest: String,
    val totalPrice: Int,
    val userName: String
)
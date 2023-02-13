package com.gram15inch.domain.model.order.add.request

import com.gram15inch.domain.model.order.add.request.Menu

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
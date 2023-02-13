package com.gram15inch.domain.model.cart

import com.gram15inch.domain.model.store.StoreDetail

data class StoreCart(
    val id: Int,
    val AllMenuPrice: Int,
    val addr: String,
    val fee: Int,
    val store: StoreDetail,
)


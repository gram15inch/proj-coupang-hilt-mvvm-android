package com.gram15inch.domain.repository

import com.gram15inch.domain.model.cart.CartMenu
import com.gram15inch.domain.model.store.StoreDetail


interface CartRepository {
    var cartMenus : MutableList<CartMenu>
    val cartStore : MutableList<StoreDetail>
    val devrMsgs : ArrayList<String>
}


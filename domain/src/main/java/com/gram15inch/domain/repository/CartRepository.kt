package com.gram15inch.domain.repository

import com.clone.mycoupang.domain.model.store.CartMenu
import com.clone.mycoupang.domain.model.store.StoreDetail
import javax.inject.Inject


interface CartRepository {
    var cartMenus : MutableList<CartMenu>
    val cartStore : MutableList<StoreDetail>
    val devrMsgs : ArrayList<String>
}


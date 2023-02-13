package com.gram15inch.domain.model.store

import com.gram15inch.domain.model.menu.MenuDetail
import com.gram15inch.domain.model.menu.MenuType

data class StoreCart(
    val id: Int,
    val AllMenuPrice: Int,
    val addr: String,
    val fee: Int,
    val store: StoreDetail,
)


data class CartMenu(
    val menuId:Int,
    val menu: MenuDetail,
    val count: Int,
    val typeList: List<MenuType>
)


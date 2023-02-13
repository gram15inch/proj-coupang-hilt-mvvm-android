package com.gram15inch.domain.model.cart

import com.gram15inch.domain.model.menu.MenuDetail
import com.gram15inch.domain.model.menu.MenuType

data class CartMenu(
    val menuId:Int,
    val menu: MenuDetail,
    val count: Int,
    val typeList: List<MenuType>
)
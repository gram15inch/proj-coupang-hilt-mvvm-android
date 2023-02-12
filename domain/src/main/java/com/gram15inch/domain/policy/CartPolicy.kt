package com.clone.mycoupang.domain.policy

import android.annotation.SuppressLint
import com.gram15inch.domain.model.menu.MenuType
import com.clone.mycoupang.domain.model.store.CartMenu

fun calculateCartMenuPrice(
    menuPrice: Int,
    menuCount: Int,
    menuOptions: List<MenuType>
): Int {
    // Timber.tag("calculate").d("list ->${menuOptions}")
    var allPrice = 0
    var necessaryPrice = 0
    menuOptions.forEach {
        if (it.necessary) {
            necessaryPrice += it.optList.firstOrNull()?.price ?: 0
        } else if (it.multiple) {
            it.optList.forEach {
                allPrice += it.price
            }
        }
    }
    allPrice += ((necessaryPrice + menuPrice) * menuCount)
    return allPrice
}


@SuppressLint("SuspiciousIndentation")
fun calculateCartPrice(
    cartMenus: List<CartMenu>
): Int {
    //Timber.tag("calculate").d("cartMenu->${cartMenus.firstOrNull()?.typeList}")
    var allPrice = 0
    cartMenus.forEach { cartMenu ->
        allPrice += calculateCartMenuPrice(
            cartMenu.menu.price, cartMenu.count, cartMenu.typeList
        )
    }
    return allPrice
}


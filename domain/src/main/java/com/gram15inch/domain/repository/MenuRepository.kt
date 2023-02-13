package com.gram15inch.domain.repository

import com.gram15inch.domain.model.menu.MenuDetail


interface MenuRepository{
    suspend fun getMenu(storeId:Int, menuId:Int):  MenuDetail?
}

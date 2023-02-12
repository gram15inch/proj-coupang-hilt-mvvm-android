package com.gram15inch.domain.repository

import com.clone.mycoupang.data.remote.MenuApiService
import com.clone.mycoupang.data.remote.model.menu.MenuDetailResponse
import retrofit2.Response
import javax.inject.Inject


interface MenuRepository{
    suspend fun getMenu(storeId:Int, menuId:Int): Response<MenuDetailResponse>
}

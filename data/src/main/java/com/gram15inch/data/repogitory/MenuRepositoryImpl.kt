package com.gram15inch.data.repogitory

import com.clone.mycoupang.data.remote.MenuApiService
import com.clone.mycoupang.data.remote.model.menu.MenuDetailResponse
import com.gram15inch.domain.repository.MenuRepository
import retrofit2.Response
import javax.inject.Inject


class MenuRepositoryImpl @Inject constructor(private val menuApiService: MenuApiService):
    MenuRepository {
    override suspend fun getMenu(storeId:Int, menuId:Int): Response<MenuDetailResponse> {
        return menuApiService.getMenu(storeId,menuId)
    }

}
package com.gram15inch.data.datasource.remote.impl

import com.gram15inch.data.datasource.remote.MenuRemoteDataSource
import com.gram15inch.data.datasource.remote.apiservice.MenuApiService
import com.gram15inch.data.datasource.remote.model.menu.MenuDetailResponse
import com.gram15inch.data.policy.responseErrorHandle
import javax.inject.Inject

class MenuRemoteDataSourceImpl@Inject constructor(private val menuApiService: MenuApiService):MenuRemoteDataSource {

    override suspend fun getMenuResponse(storeId: Int, menuId: Int): MenuDetailResponse {
        return menuApiService.getMenu(storeId,menuId).run { responseErrorHandle(this) }
    }
}
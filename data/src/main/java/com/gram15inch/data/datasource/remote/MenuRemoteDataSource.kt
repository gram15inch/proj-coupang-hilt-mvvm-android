package com.gram15inch.data.datasource.remote

import com.gram15inch.data.datasource.remote.model.menu.MenuDetailResponse
import retrofit2.Response
import retrofit2.http.Path

interface MenuRemoteDataSource {
    suspend fun getMenuResponse(storeId:Int, menuId:Int): MenuDetailResponse
}
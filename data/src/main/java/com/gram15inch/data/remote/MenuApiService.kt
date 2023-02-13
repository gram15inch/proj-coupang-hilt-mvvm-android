package com.gram15inch.data.remote

import com.gram15inch.data.remote.model.menu.MenuDetailResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface MenuApiService  {
    @GET("stores/{storeId}/{menuId}")
    suspend fun getMenu(
        @Path("storeId") storeId:Int ,
        @Path("menuId") menuId:Int
    ): Response<MenuDetailResponse>
}

/*
*
* https://prod.happypage.shop/coupang-eats/stores/2/6
* */
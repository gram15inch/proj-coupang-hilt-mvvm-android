package com.gram15inch.data.remote

import com.gram15inch.data.remote.model.store.category.HomeCategoryResponse
import com.gram15inch.data.remote.model.store.detail.StoreDetailResponse
import com.gram15inch.data.remote.model.store.pick.PickStoreResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface StoreApiService {

    @GET("stores/choose-stores/no-login")//테스트 완료
    suspend fun getChooseStore(
        @Query("latitude") latitude:String,
        @Query("longitude") longitude:String
    ): Response<PickStoreResponse>

      @GET("stores/categories")
    suspend fun getHomeCategory(): Response<HomeCategoryResponse>
    @GET("stores/{id}") // 테스트 완료
    suspend fun getStoreDetail(
        @Path("id") str:Int
    ): Response<StoreDetailResponse>


}

interface StoreApiServiceGiin{



}
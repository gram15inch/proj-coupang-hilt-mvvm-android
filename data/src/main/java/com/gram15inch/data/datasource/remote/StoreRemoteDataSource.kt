package com.gram15inch.data.datasource.remote

import com.gram15inch.data.datasource.remote.model.store.category.HomeCategoryResponse
import com.gram15inch.data.remote.model.store.detail.StoreDetailResponse
import com.gram15inch.data.remote.model.store.pick.PickStoreResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface StoreRemoteDataSource {


    suspend fun getChooseStoreResponse(latitude:String, longitude:String)
    : PickStoreResponse

    suspend fun getHomeCategoryResponse()
    : HomeCategoryResponse

    suspend fun getStoreDetailResponse(str:Int)
    : StoreDetailResponse

}
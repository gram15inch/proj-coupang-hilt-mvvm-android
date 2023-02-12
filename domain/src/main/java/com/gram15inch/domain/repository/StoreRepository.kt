package com.gram15inch.domain.repository

import com.clone.mycoupang.data.remote.StoreApiService
import com.clone.mycoupang.data.remote.StoreApiServiceGiin
import com.clone.mycoupang.data.remote.model.store.category.HomeCategoryResponse
import com.clone.mycoupang.data.remote.model.store.detail.StoreDetailResponse
import com.clone.mycoupang.data.remote.model.store.pick.PickStoreRequest
import com.clone.mycoupang.data.remote.model.store.pick.PickStoreResponse
import retrofit2.Response
import javax.inject.Inject

interface StoreRepository{
     suspend fun getPickStore(request: PickStoreRequest): Response<PickStoreResponse>
    suspend fun getHomeCategory(): Response<HomeCategoryResponse>
    suspend fun getStoreDetail(id:Int):Response<StoreDetailResponse>
}


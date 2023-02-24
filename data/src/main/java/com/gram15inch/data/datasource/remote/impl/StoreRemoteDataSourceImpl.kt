package com.gram15inch.data.datasource.remote.impl

import com.gram15inch.data.datasource.remote.StoreRemoteDataSource
import com.gram15inch.data.datasource.remote.apiservice.StoreApiService
import com.gram15inch.data.datasource.remote.model.store.category.HomeCategoryResponse
import com.gram15inch.data.policy.responseErrorHandle
import com.gram15inch.data.remote.model.store.detail.StoreDetailResponse
import com.gram15inch.data.remote.model.store.pick.PickStoreResponse
import javax.inject.Inject

class StoreRemoteDataSourceImpl@Inject constructor(private val storeApiService: StoreApiService):StoreRemoteDataSource {
    override suspend fun getChooseStoreResponse(
        latitude: String,
        longitude: String
    ): PickStoreResponse {
        return storeApiService.getChooseStore(latitude,longitude).run { responseErrorHandle(this) }
    }

    override suspend fun getHomeCategoryResponse(): HomeCategoryResponse {
        return storeApiService.getHomeCategory().run { responseErrorHandle(this) }
    }

    override suspend fun getStoreDetailResponse(str: Int): StoreDetailResponse {
       return storeApiService.getStoreDetail(str).run { responseErrorHandle(this) }
    }
}
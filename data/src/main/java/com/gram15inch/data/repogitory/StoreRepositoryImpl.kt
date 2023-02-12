package com.gram15inch.data.repogitory

import com.clone.mycoupang.data.remote.StoreApiService
import com.clone.mycoupang.data.remote.model.store.category.HomeCategoryResponse
import com.clone.mycoupang.data.remote.model.store.detail.StoreDetailResponse
import com.clone.mycoupang.data.remote.model.store.pick.PickStoreRequest
import com.clone.mycoupang.data.remote.model.store.pick.PickStoreResponse
import com.gram15inch.domain.repository.StoreRepository
import retrofit2.Response
import javax.inject.Inject


class StoreRepositoryImpl @Inject constructor (private val storeApiService: StoreApiService):
    StoreRepository {

    override suspend fun getPickStore(request: PickStoreRequest): Response<PickStoreResponse> {
        return storeApiService.getChooseStore(request.latitude,request.longitude)
    }
    override  suspend fun getHomeCategory(): Response<HomeCategoryResponse> {
        return storeApiService.getHomeCategory()
    }

    override  suspend fun getStoreDetail(id:Int):Response<StoreDetailResponse>{
        return storeApiService.getStoreDetail(id)
    }

}
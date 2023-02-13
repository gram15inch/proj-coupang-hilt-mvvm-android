package com.gram15inch.data.repogitory

import com.gram15inch.domain.model.store.detail.Store
import com.gram15inch.domain.model.store.StoreDetail
import com.gram15inch.domain.model.store.home.HomeCategory
import com.gram15inch.data.converter.StoreConverter
import com.gram15inch.data.remote.StoreApiService
import com.gram15inch.domain.model.store.pick.PickStoreRequest
import com.gram15inch.domain.repository.StoreRepository
import javax.inject.Inject


class StoreRepositoryImpl @Inject constructor (private val storeApiService: StoreApiService):
    StoreRepository {

    override suspend fun getPickStore(request: PickStoreRequest): List<Store> {

        storeApiService.getChooseStore(request.latitude,request.latitude).body().also {
            return if (it?.isSuccess == true)
                it.result?.map { remote -> StoreConverter.toStore(remote) }?: emptyList()
            else
                emptyList()
        }

    }
    override suspend fun getHomeCategory(): List<HomeCategory> {
        storeApiService.getHomeCategory().body().also {
            return if (it?.isSuccess == true)
                it.result.map { remote -> StoreConverter.toHomeCategory(remote) }
            else
                emptyList()
        }
    }

    override suspend fun getStoreDetail(id:Int): StoreDetail?{
        storeApiService.getStoreDetail(id).body().also {
            if (it?.isSuccess == true)
                if (it.result != null)
                    return StoreConverter.toStoreDetail(it.result)
        }

        return null
    }

}
package com.gram15inch.data.repogitory

import com.gram15inch.domain.model.store.detail.Store
import com.gram15inch.domain.model.store.StoreDetail
import com.gram15inch.domain.model.store.home.HomeCategory
import com.gram15inch.data.converter.StoreConverter
import com.gram15inch.data.datasource.remote.StoreRemoteDataSource
import com.gram15inch.data.datasource.remote.apiservice.StoreApiService
import com.gram15inch.domain.model.store.pick.PickStoreRequest
import com.gram15inch.domain.repository.StoreRepository
import javax.inject.Inject


class StoreRepositoryImpl @Inject constructor (private val storeRemoteDataSource: StoreRemoteDataSource):
    StoreRepository {

    override suspend fun getPickStore(request: PickStoreRequest): List<Store> {

        storeRemoteDataSource.getChooseStoreResponse(request.latitude,request.latitude).also {
            return if (it.isSuccess)
                it.result?.map { remote -> StoreConverter.toStore(remote) }?: emptyList()
            else
                emptyList()
        }

    }
    override suspend fun getHomeCategory(): List<HomeCategory> {
        storeRemoteDataSource.getHomeCategoryResponse().also {
            return if (it.isSuccess)
                it.result.map { remote -> StoreConverter.toHomeCategory(remote) }
            else
                emptyList()
        }
    }

    override suspend fun getStoreDetail(id:Int): StoreDetail?{
        storeRemoteDataSource.getStoreDetailResponse(id).also {
            if (it.isSuccess)
                if (it.result != null)
                    return StoreConverter.toStoreDetail(it.result)
        }
        return null
    }

}
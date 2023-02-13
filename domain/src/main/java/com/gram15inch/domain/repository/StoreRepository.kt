package com.gram15inch.domain.repository

import com.gram15inch.domain.model.store.Store
import com.gram15inch.domain.model.store.StoreDetail
import com.gram15inch.domain.model.store.home.HomeCategory
import com.gram15inch.domain.model.store.pick.PickStoreRequest

interface StoreRepository{
     suspend fun getPickStore(request: PickStoreRequest): List<Store>
    suspend fun getHomeCategory(): List<HomeCategory>
    suspend fun getStoreDetail(id:Int): StoreDetail?
}


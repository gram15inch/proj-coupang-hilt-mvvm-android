package com.gram15inch.data.datasource.remote.impl

import com.gram15inch.data.datasource.remote.OrderRemoteDataSource
import com.gram15inch.data.datasource.remote.apiservice.OrderApiService
import com.gram15inch.data.datasource.remote.model.order.add.OrderAddResponse
import com.gram15inch.data.datasource.remote.model.order.history.HistoryResponse
import com.gram15inch.data.policy.responseErrorHandle
import com.gram15inch.domain.model.order.add.OrderAddRequest
import javax.inject.Inject

class OrderRemoteDataSourceImpl @Inject constructor(private val orderApiService: OrderApiService):OrderRemoteDataSource {
    override suspend fun getHistoryResponse(userId: Int): HistoryResponse {
        return orderApiService.getHistory(userId).run { responseErrorHandle(this) }
    }

    override suspend fun postOrderAddResponse(
        storeId: Int,
        params: OrderAddRequest
    ): OrderAddResponse {
        return orderApiService.postOrderAdd(storeId,params).run { responseErrorHandle(this) }
    }
}
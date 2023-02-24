package com.gram15inch.data.repogitory

import com.gram15inch.domain.model.order.history.History
import com.gram15inch.data.converter.OrderConverter
import com.gram15inch.data.datasource.remote.OrderRemoteDataSource
import com.gram15inch.data.datasource.remote.apiservice.OrderApiService
import com.gram15inch.domain.model.order.add.OrderAddRequest
import com.gram15inch.domain.model.order.history.OrderAdd
import com.gram15inch.domain.repository.OrderRepository
import javax.inject.Inject

class OrderRepositoryImpl @Inject constructor(private val orderRemoteDataSource: OrderRemoteDataSource) :
    OrderRepository {

    override suspend fun getHistory(userId: Int): List<History> {
        orderRemoteDataSource.getHistoryResponse(userId).also {
            return if (it.isSuccess)
                it.result.map { remote -> OrderConverter.toHistory(remote) }
            else
                emptyList()
        }
    }

    override suspend fun postOrderAdd(storeId: Int, request: OrderAddRequest): OrderAdd? {

        orderRemoteDataSource.postOrderAddResponse(storeId, request).also {
            if (it.isSuccess)
                return OrderConverter.toOrderAdd(it.result)
        }
        return null
    }

}
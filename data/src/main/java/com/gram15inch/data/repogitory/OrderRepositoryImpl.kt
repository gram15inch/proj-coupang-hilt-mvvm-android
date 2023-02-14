package com.gram15inch.data.repogitory

import com.gram15inch.domain.model.order.history.History
import com.gram15inch.data.converter.OrderConverter
import com.gram15inch.data.remote.OrderApiService
import com.gram15inch.domain.model.order.add.OrderAddRequest
import com.gram15inch.domain.model.order.history.OrderAdd
import com.gram15inch.domain.repository.OrderRepository
import javax.inject.Inject

class OrderRepositoryImpl @Inject constructor(private val orderApiService: OrderApiService) :
    OrderRepository {

    override suspend fun getHistory(userId: Int): List<History> {
        orderApiService.getHistory(userId).body().also {
            if (it?.isSuccess == true)
                return it.result.map { remote -> OrderConverter.toHistory(remote) }
            else
                return emptyList()
        }
    }

    override suspend fun postOrderAdd(storeId: Int, request: OrderAddRequest): OrderAdd? {

        orderApiService.postOrderAdd(storeId, request).body().also {
            if (it?.isSuccess == true)
                if (it.result != null)
                    return OrderConverter.toOrderAdd(it.result)
        }

        return null
    }

}
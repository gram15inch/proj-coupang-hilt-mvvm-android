package com.gram15inch.data.repogitory

import com.clone.mycoupang.data.remote.OrderApiService
import com.clone.mycoupang.data.remote.model.order.add.OrderAddResponse
import com.clone.mycoupang.data.remote.model.order.add.request.OrderAddRequest
import com.clone.mycoupang.data.remote.model.order.history.HistoryResponse
import com.clone.mycoupang.domain.repository.OrderRepository
import retrofit2.Response
import javax.inject.Inject

class OrderRepositoryImpl @Inject constructor (private val orderApiService: OrderApiService):
    OrderRepository {

    override suspend fun getHistory(userId:Int): Response<HistoryResponse> {
        return orderApiService.getHistory(userId)
    }
    override  suspend fun postOrderAdd(storeId:Int, request: OrderAddRequest): Response<OrderAddResponse> {
        return orderApiService.postOrderAdd(storeId,request)
    }

}
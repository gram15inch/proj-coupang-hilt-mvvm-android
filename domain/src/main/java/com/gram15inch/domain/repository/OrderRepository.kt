package com.clone.mycoupang.domain.repository

import com.clone.mycoupang.data.remote.OrderApiService
import com.clone.mycoupang.data.remote.model.order.add.OrderAddResponse
import com.clone.mycoupang.data.remote.model.order.add.request.OrderAddRequest
import com.clone.mycoupang.data.remote.model.order.history.HistoryResponse
import retrofit2.Response
import javax.inject.Inject


interface OrderRepository{
    suspend fun getHistory(userId:Int): Response<HistoryResponse>

    suspend fun postOrderAdd(storeId:Int, request: OrderAddRequest): Response<OrderAddResponse>

}



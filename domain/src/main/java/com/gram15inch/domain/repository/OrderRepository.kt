package com.gram15inch.domain.repository


import com.gram15inch.domain.model.order.add.request.OrderAddRequest
import com.gram15inch.domain.model.order.history.History
import com.gram15inch.domain.model.order.history.OrderAdd


interface OrderRepository{
    suspend fun getHistory(userId:Int): List<History>

    suspend fun postOrderAdd(storeId:Int, request: OrderAddRequest): OrderAdd?

}



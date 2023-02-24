package com.gram15inch.data.datasource.remote

import com.gram15inch.data.datasource.remote.model.order.add.OrderAddResponse
import com.gram15inch.data.datasource.remote.model.order.history.HistoryResponse
import com.gram15inch.domain.model.order.add.OrderAddRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface OrderRemoteDataSource {


    suspend fun getHistoryResponse(
        userId:Int,
    ): HistoryResponse


    suspend fun postOrderAddResponse(storeId:Int, params: OrderAddRequest): OrderAddResponse
}
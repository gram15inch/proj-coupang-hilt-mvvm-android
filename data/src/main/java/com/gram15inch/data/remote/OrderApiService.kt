package com.clone.mycoupang.data.remote

import com.clone.mycoupang.data.remote.model.order.add.OrderAddResponse
import com.clone.mycoupang.data.remote.model.order.add.request.OrderAddRequest
import com.clone.mycoupang.data.remote.model.order.history.HistoryResponse
import com.clone.mycoupang.data.remote.model.user.SignUpRequest
import com.clone.mycoupang.data.remote.model.user.SignUpResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface OrderApiService {
    @GET("users/{userId}/orders")
    suspend fun getHistory(
        @Path("userId") userId:Int,
    ): Response<HistoryResponse>

    @POST("stores/{storeId}/orders")
    suspend fun postOrderAdd(
        @Path("storeId") storeId:Int,
        @Body params: OrderAddRequest
    ): Response<OrderAddResponse>

}
/*  https://prod.happypage.shop/coupang-eats/users/1/orders*/
/*  https://prod.happypage.shop/coupang-eats/users/2/orders*/
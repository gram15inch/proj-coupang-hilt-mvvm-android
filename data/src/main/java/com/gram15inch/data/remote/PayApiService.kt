package com.clone.mycoupang.data.remote

import com.clone.mycoupang.data.remote.model.address.AddressResponse
import com.clone.mycoupang.data.remote.model.pay.PayResponse
import retrofit2.Response
import retrofit2.http.GET

interface PayApiService  {

    @GET("users/payments")
    suspend fun getPayments(): Response<PayResponse>

}
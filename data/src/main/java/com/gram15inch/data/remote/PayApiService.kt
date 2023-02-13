package com.gram15inch.data.remote

import com.gram15inch.data.remote.model.pay.PayResponse
import retrofit2.Response
import retrofit2.http.GET

interface PayApiService  {

    @GET("users/payments")
    suspend fun getPayments(): Response<PayResponse>

}
package com.gram15inch.domain.repository

import com.clone.mycoupang.data.remote.AddressApiService
import com.clone.mycoupang.data.remote.PayApiService
import com.clone.mycoupang.data.remote.model.address.AddressResponse
import com.clone.mycoupang.data.remote.model.pay.PayResponse
import retrofit2.Response
import javax.inject.Inject


interface PayRepository{
    suspend fun getPayments(): Response<PayResponse>
}


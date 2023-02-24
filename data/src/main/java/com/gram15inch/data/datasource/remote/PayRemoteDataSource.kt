package com.gram15inch.data.datasource.remote

import com.gram15inch.data.datasource.remote.model.pay.PayResponse
import retrofit2.Response

interface PayRemoteDataSource {
    suspend fun getPaymentsResponse(): PayResponse
}
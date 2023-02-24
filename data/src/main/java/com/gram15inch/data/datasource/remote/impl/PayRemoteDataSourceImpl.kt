package com.gram15inch.data.datasource.remote.impl

import com.gram15inch.data.datasource.remote.PayRemoteDataSource
import com.gram15inch.data.datasource.remote.apiservice.PayApiService
import com.gram15inch.data.datasource.remote.model.pay.PayResponse
import com.gram15inch.data.policy.responseErrorHandle
import retrofit2.Response
import javax.inject.Inject

class PayRemoteDataSourceImpl@Inject constructor(private val payApiService: PayApiService):PayRemoteDataSource {
    override suspend fun getPaymentsResponse(): PayResponse {
        return payApiService.getPayments().run { responseErrorHandle(this) }
    }
}
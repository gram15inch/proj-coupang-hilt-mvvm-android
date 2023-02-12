package com.gram15inch.data.repogitory

import com.clone.mycoupang.data.remote.PayApiService
import com.clone.mycoupang.data.remote.model.pay.PayResponse
import com.gram15inch.domain.repository.PayRepository
import retrofit2.Response
import javax.inject.Inject


class PayRepositoryImpl @Inject constructor (val payApiService: PayApiService): PayRepository {
    override suspend fun getPayments(): Response<PayResponse> {
        return payApiService.getPayments()
    }
}
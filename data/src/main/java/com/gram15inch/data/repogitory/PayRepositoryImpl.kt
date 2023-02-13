package com.gram15inch.data.repogitory

import com.gram15inch.domain.model.pay.Pay
import com.gram15inch.data.converter.PayConverter
import com.gram15inch.data.remote.PayApiService
import com.gram15inch.domain.repository.PayRepository
import javax.inject.Inject


class PayRepositoryImpl @Inject constructor (private val payApiService: PayApiService)
    : PayRepository {
    override suspend fun getPayments(): List<Pay> {

        payApiService.getPayments().body().also {
            return if (it?.isSuccess == true)
                PayConverter.toPay(it.result)
            else
                emptyList()
        }

    }
}
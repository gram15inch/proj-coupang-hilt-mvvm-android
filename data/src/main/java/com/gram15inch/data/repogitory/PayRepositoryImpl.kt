package com.gram15inch.data.repogitory

import com.gram15inch.domain.model.pay.Pay
import com.gram15inch.data.converter.PayConverter
import com.gram15inch.data.datasource.remote.PayRemoteDataSource
import com.gram15inch.data.datasource.remote.apiservice.PayApiService
import com.gram15inch.domain.repository.PayRepository
import javax.inject.Inject


class PayRepositoryImpl @Inject constructor (private val payRemoteDataSource: PayRemoteDataSource)
    : PayRepository {
    override suspend fun getPayments(): List<Pay> {

        payRemoteDataSource.getPaymentsResponse().also {
            return if (it.isSuccess)
                PayConverter.toPay(it.result)
            else
                emptyList()
        }

    }
}
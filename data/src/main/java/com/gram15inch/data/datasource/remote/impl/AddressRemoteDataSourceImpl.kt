package com.gram15inch.data.datasource.remote.impl

import com.gram15inch.data.datasource.remote.AddressRemoteDataSource
import com.gram15inch.data.datasource.remote.apiservice.AddressApiService
import com.gram15inch.data.datasource.remote.model.address.AddressResponse
import com.gram15inch.data.policy.responseErrorHandle
import javax.inject.Inject

class AddressRemoteDataSourceImpl @Inject constructor(private val addressApiService: AddressApiService) :AddressRemoteDataSource {
    override suspend fun getAddressResponse(): AddressResponse {
        return addressApiService.getAddress().run { responseErrorHandle(this) }
    }
}
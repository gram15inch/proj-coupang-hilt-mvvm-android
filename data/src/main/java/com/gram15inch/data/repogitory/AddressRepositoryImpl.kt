package com.gram15inch.data.repogitory

import com.gram15inch.data.converter.AddressConverter
import com.gram15inch.data.datasource.remote.AddressRemoteDataSource
import com.gram15inch.data.datasource.remote.apiservice.AddressApiService
import com.gram15inch.domain.model.address.Address
import com.gram15inch.domain.repository.AddressRepository
import javax.inject.Inject

class AddressRepositoryImpl @Inject constructor (private val addressRemoteDataSource: AddressRemoteDataSource):
    AddressRepository {
    override suspend fun getAddress(): List<Address> {

        addressRemoteDataSource.getAddressResponse()
            .also {
                return if (it.isSuccess)
                    it.result.map {remote-> AddressConverter.toAddress(remote) }
                else
                    emptyList()
            }
    }

}
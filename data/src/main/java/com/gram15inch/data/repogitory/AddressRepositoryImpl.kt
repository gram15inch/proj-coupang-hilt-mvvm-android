package com.gram15inch.data.repogitory

import com.gram15inch.data.converter.AddressConverter
import com.gram15inch.data.remote.AddressApiService
import com.gram15inch.domain.model.address.Address
import com.gram15inch.domain.repository.AddressRepository
import javax.inject.Inject

class AddressRepositoryImpl @Inject constructor (private val addressApiService: AddressApiService):
    AddressRepository {
    override suspend fun getAddress(): List<Address> {
        addressApiService.getAddress().body()
            .also {
                if (it?.isSuccess == true)
                    return it.result.map {remote-> AddressConverter.toAddress(remote) }
                else
                    return emptyList()
            }
    }

}
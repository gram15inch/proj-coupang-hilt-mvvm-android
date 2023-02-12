package com.gram15inch.data.repogitory

import com.clone.mycoupang.data.remote.AddressApiService
import com.clone.mycoupang.data.remote.model.address.AddressResponse
import com.gram15inch.domain.repository.AddressRepository
import retrofit2.Response
import javax.inject.Inject

class AddressRepositoryImpl @Inject constructor (private val addressApiService: AddressApiService):
    AddressRepository {
    override suspend fun getAddress(): Response<AddressResponse> {
        addressApiService.getAddress()
        return addressApiService.getAddress()
    }

}
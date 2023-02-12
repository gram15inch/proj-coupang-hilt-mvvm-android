package com.gram15inch.domain.repository

import com.clone.mycoupang.data.remote.AddressApiService
import com.clone.mycoupang.data.remote.model.address.AddressResponse
import retrofit2.Response
import javax.inject.Inject



interface AddressRepository {
    suspend fun getAddress(): Response<AddressResponse>
}


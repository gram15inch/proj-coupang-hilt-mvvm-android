package com.gram15inch.data.remote

import com.gram15inch.data.remote.model.address.AddressResponse
import retrofit2.Response
import retrofit2.http.GET

interface AddressApiService  {

    @GET("users/addresses")
    suspend fun getAddress(): Response<AddressResponse> // 테스트 완료

}
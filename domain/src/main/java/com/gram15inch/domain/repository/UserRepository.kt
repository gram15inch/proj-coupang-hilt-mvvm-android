package com.gram15inch.domain.repository

import com.clone.mycoupang.data.remote.UserApiService
import com.clone.mycoupang.data.remote.model.address.AddressResponse
import com.clone.mycoupang.data.remote.model.user.LoginRequest
import com.clone.mycoupang.data.remote.model.user.LoginResponse
import com.clone.mycoupang.data.remote.model.user.SignUpRequest
import com.clone.mycoupang.data.remote.model.user.SignUpResponse
import retrofit2.Response
import retrofit2.Retrofit
import javax.inject.Inject


interface UserRepository {
    suspend fun postLogin(request: LoginRequest): Response<LoginResponse>
    suspend fun postSignUp(request: SignUpRequest): Response<SignUpResponse>
}


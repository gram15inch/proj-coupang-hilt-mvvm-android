package com.clone.mycoupang.data.remote

import com.clone.mycoupang.data.remote.model.address.AddressResponse
import com.clone.mycoupang.data.remote.model.user.LoginRequest
import com.clone.mycoupang.data.remote.model.user.LoginResponse
import com.clone.mycoupang.data.remote.model.user.SignUpRequest
import com.clone.mycoupang.data.remote.model.user.SignUpResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface UserApiService {

    @POST("users/logIn")
    suspend fun postLogin(
        @Body params: LoginRequest
    ): Response<LoginResponse>
    @POST("users")
    suspend fun postSignUp(
        @Body params: SignUpRequest
    ): Response<SignUpResponse>

    /*    @POST("logIn")
    suspend fun postLogin(
        @Body params: LoginRequest
    ): Response<LoginResponse>
    @POST("users")
    suspend fun postSignUp(
        @Body params: SignUpRequest
    ): Response<SignUpResponse>*/

}
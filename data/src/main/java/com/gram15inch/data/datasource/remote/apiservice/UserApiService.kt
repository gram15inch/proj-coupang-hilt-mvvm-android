package com.gram15inch.data.datasource.remote.apiservice

import com.gram15inch.data.datasource.remote.model.user.LoginResponse
import com.gram15inch.data.datasource.remote.model.user.SignUpResponse
import com.gram15inch.domain.model.user.LoginRequest
import com.gram15inch.domain.model.user.SignUpRequest
import retrofit2.Response
import retrofit2.http.Body
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

}
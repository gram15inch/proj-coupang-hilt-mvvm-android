package com.gram15inch.data.remote

import com.gram15inch.data.remote.model.user.LoginResponse
import com.gram15inch.domain.model.user.SignUpRequest
import com.gram15inch.data.remote.model.user.SignUpResponse
import com.gram15inch.domain.model.user.LoginRequest
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
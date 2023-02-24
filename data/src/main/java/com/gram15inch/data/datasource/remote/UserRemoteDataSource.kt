package com.gram15inch.data.datasource.remote

import com.gram15inch.data.datasource.remote.model.user.LoginResponse
import com.gram15inch.data.datasource.remote.model.user.SignUpResponse
import com.gram15inch.domain.model.user.LoginRequest
import com.gram15inch.domain.model.user.SignUpRequest
import retrofit2.Response


interface UserRemoteDataSource {

    suspend fun postLoginResponse(params: LoginRequest)
    : LoginResponse

    suspend fun postSignUpResponse(params: SignUpRequest)
    : SignUpResponse

}
package com.gram15inch.data.datasource.remote.impl

import com.gram15inch.data.datasource.remote.UserRemoteDataSource
import com.gram15inch.data.datasource.remote.apiservice.UserApiService
import com.gram15inch.data.datasource.remote.model.user.LoginResponse
import com.gram15inch.data.datasource.remote.model.user.SignUpResponse
import com.gram15inch.data.policy.responseErrorHandle
import com.gram15inch.domain.model.user.LoginRequest
import com.gram15inch.domain.model.user.SignUpRequest
import javax.inject.Inject

class UserRemoteDataSourceImpl@Inject constructor(private val userApiService: UserApiService):UserRemoteDataSource {
    override suspend fun postLoginResponse(params: LoginRequest): LoginResponse {
       return userApiService.postLogin(params).run { responseErrorHandle(this) }
    }

    override suspend fun postSignUpResponse(params: SignUpRequest): SignUpResponse {
        return userApiService.postSignUp(params).run { responseErrorHandle(this) }
    }

}
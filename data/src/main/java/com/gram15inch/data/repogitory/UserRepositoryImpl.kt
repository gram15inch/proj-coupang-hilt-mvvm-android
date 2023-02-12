package com.gram15inch.data.repogitory

import com.clone.mycoupang.data.remote.UserApiService
import com.clone.mycoupang.data.remote.model.user.LoginRequest
import com.clone.mycoupang.data.remote.model.user.LoginResponse
import com.clone.mycoupang.data.remote.model.user.SignUpRequest
import com.clone.mycoupang.data.remote.model.user.SignUpResponse
import com.gram15inch.domain.repository.UserRepository
import retrofit2.Response
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(private val userApiService: UserApiService) :
    UserRepository {

   override suspend fun postLogin(request: LoginRequest): Response<LoginResponse> {
        return userApiService.postLogin(request)
    }

    override suspend fun postSignUp(request: SignUpRequest): Response<SignUpResponse> {
        return userApiService.postSignUp(request)
    }
}
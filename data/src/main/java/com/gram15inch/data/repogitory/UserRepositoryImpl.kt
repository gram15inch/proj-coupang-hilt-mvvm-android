package com.gram15inch.data.repogitory

import com.gram15inch.data.remote.UserApiService

import com.gram15inch.data.remote.model.user.LoginResponse
import com.gram15inch.data.remote.model.user.SignUpResponse
import com.gram15inch.domain.model.user.LoginRequest
import com.gram15inch.domain.model.user.SignUpRequest
import com.gram15inch.domain.repository.UserRepository
import retrofit2.Response
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(private val userApiService: UserApiService) :
    UserRepository {
// todo 여기부터
   override suspend fun postLogin(request: LoginRequest): Response<LoginResponse> {
        return userApiService.postLogin(request)
    }

    override suspend fun postSignUp(request: SignUpRequest): Response<SignUpResponse> {
        return userApiService.postSignUp(request)
    }
}
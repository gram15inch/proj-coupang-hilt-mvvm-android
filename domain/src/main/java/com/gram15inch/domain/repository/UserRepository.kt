package com.gram15inch.domain.repository


import com.gram15inch.domain.model.user.Login
import com.gram15inch.domain.model.user.LoginRequest
import com.gram15inch.domain.model.user.SignUp
import com.gram15inch.domain.model.user.SignUpRequest


interface UserRepository {
    suspend fun postLogin(request: LoginRequest): Login?
    suspend fun postSignUp(request: SignUpRequest): SignUp?
}


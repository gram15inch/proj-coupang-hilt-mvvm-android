package com.gram15inch.data.repogitory

import com.gram15inch.data.converter.UserConverter
import com.gram15inch.data.datasource.remote.UserRemoteDataSource
import com.gram15inch.data.datasource.remote.apiservice.UserApiService
import com.gram15inch.domain.model.user.Login
import com.gram15inch.domain.model.user.LoginRequest
import com.gram15inch.domain.model.user.SignUp
import com.gram15inch.domain.model.user.SignUpRequest
import com.gram15inch.domain.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(private val userRemoteDataSource: UserRemoteDataSource) :
    UserRepository {

   override suspend fun postLogin(request: LoginRequest): Login? {
       userRemoteDataSource.postLoginResponse(request).also {
           if (it.isSuccess)
               if (it.remoteLogin != null)
                   return UserConverter.toLogin(it.remoteLogin)
       }
    return null

   }

    override suspend fun postSignUp(request: SignUpRequest): SignUp? {
        userRemoteDataSource.postSignUpResponse(request).also {
            if (it.isSuccess)
                if (it.remoteSignUp != null)
                    return UserConverter.toSignUp(it.remoteSignUp)
        }
        return null
    }
}
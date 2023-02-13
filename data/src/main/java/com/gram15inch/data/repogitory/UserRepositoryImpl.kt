package com.gram15inch.data.repogitory

import com.gram15inch.data.converter.UserConverter
import com.gram15inch.data.remote.UserApiService
import com.gram15inch.domain.model.user.Login
import com.gram15inch.domain.model.user.SignUp
import com.gram15inch.domain.model.user.SignUpRequest
import com.gram15inch.domain.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(private val userApiService: UserApiService) :
    UserRepository {

   override suspend fun postLogin(request: LoginRequest): Login? {
       userApiService.postLogin(request).body().also {
           if (it?.isSuccess == true)
               if (it.remoteLogin != null)
                   return UserConverter.toLogin(it.remoteLogin)
       }
    return null

   }

    override suspend fun postSignUp(request: SignUpRequest): SignUp? {
        userApiService.postSignUp(request).body().also {
            if (it?.isSuccess == true)
                if (it.remoteSignUp != null)
                    return UserConverter.toSignUp(it.remoteSignUp)
        }
        return null
    }
}
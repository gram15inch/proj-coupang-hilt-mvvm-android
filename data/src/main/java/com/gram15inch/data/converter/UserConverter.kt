package com.gram15inch.data.converter

import com.clone.mycoupang.data.remote.model.user.RemoteSignUp
import com.gram15inch.data.remote.model.user.RemoteLogin
import com.gram15inch.domain.model.user.Login
import com.gram15inch.domain.model.user.SignUp

object UserConverter {
    fun toLogin(remote : RemoteLogin ): Login{
        return Login(remote.jwt,
            remote.userIdx
        )
    }

    fun toSignUp(remote:RemoteSignUp):SignUp{
        return SignUp(
            remote.jwt,
            remote.userIdx
        )
    }

}
package com.gram15inch.data.policy

import com.gram15inch.domain.policy.ResponseException
import retrofit2.Response

fun <T> responseErrorHandle(response: Response<T>):T{
    if(response.isSuccessful)
        response.body().also {
            if(it!=null)
                return it
            else
                throw ResponseException("바디없음")
        }
    else {
        when(response.code()){
            else->{
                throw ResponseException("요청 실패 code:${response.code()}")
            }
        }
    }
}

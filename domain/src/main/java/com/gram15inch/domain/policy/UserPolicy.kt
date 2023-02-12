package com.clone.mycoupang.domain.policy

import timber.log.Timber


enum class LoginState { SUCCESS, FAIL, EMAIL_ERROR, PW_ERROR, NONE }
enum class SignUpState { SUCCESS, FAIL , NONE}


fun verifyEmail(email: String): Boolean {
    Timber.tag("password").d("em: $email")
    return email.any { it == '@' }
}

fun verifyPassword(pw: String): Boolean {
    Timber.tag("password").d("pw: $pw")
    return pw != ""
}
fun verifySignUpPassword(pw: String): Boolean {
    Timber.tag("password").d("sign pw: $pw")
    return  pw.length>=8
}

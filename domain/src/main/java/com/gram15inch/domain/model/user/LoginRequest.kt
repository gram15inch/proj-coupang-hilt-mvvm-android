package com.gram15inch.domain.model.user

import com.squareup.moshi.JsonClass

data class LoginRequest(
    val email: String,
    val password: String
)
package com.gram15inch.domain.model.user

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


data class SignUpRequest(
    val agree: Int,
    val email: String,
    val password: String,
    val phoneNumber: String,
    val username: String
)
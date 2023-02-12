package com.clone.mycoupang.data.remote.model.user

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class SignUpRequest(
    val agree: Int,
    val email: String,
    val password: String,
    val phoneNumber: String,
    val username: String
)
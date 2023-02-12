package com.clone.mycoupang.data.remote.model.user

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class LoginRequest(
    val email: String,
    val password: String
)
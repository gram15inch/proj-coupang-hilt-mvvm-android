package com.clone.mycoupang.data.remote.model.user

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RemoteLogin(
    val jwt: String,
    val userIdx: Int
)
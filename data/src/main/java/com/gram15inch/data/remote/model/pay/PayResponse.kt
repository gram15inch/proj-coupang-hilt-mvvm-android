package com.clone.mycoupang.data.remote.model.pay

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PayResponse(
    val code: Int,
    val isSuccess: Boolean,
    val message: String,
    @Json(name = "result")
    val result: RemotePay
)
package com.clone.mycoupang.data.remote.model.order.history

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class HistoryResponse(
    val code: Int,
    val isSuccess: Boolean,
    val message: String,
    @Json(name = "result")
    val result: List<RemoteHistory>
)


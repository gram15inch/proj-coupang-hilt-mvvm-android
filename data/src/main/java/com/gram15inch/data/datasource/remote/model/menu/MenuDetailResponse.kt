package com.gram15inch.data.datasource.remote.model.menu

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class MenuDetailResponse(
    val code: Int,
    val isSuccess: Boolean,
    val message: String,
    @Json(name = "result")
    val result: com.gram15inch.data.datasource.remote.model.menu.RemoteMenuDetail?
)
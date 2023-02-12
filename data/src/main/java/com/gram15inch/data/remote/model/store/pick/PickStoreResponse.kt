package com.clone.mycoupang.data.remote.model.store.pick

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class PickStoreResponse(
    val code: Int,
    val isSuccess: Boolean,
    val message: String,
    @Json(name = "result")
    val result: List<RemotePickStore>?
)
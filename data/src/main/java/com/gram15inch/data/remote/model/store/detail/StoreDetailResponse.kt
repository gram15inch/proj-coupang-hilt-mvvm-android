package com.gram15inch.data.remote.model.store.detail

import com.gram15inch.data.remote.model.store.detail.RemoteStoreDetail
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class StoreDetailResponse(
    val code: Int,
    val isSuccess: Boolean,
    val message: String,
    @Json(name = "result")
    val result: RemoteStoreDetail?
)
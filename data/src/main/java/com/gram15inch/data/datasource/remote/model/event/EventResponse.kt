package com.gram15inch.data.datasource.remote.model.event

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class EventResponse(
    val code: Int,
    val isSuccess: Boolean,
    val message: String,
    @Json(name = "result")
    val result: List<com.gram15inch.data.datasource.remote.model.event.RemoteEvent>
)
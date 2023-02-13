package com.gram15inch.data.remote.model.event

import com.squareup.moshi.JsonClass

data class RemoteEvent(
    val endDate: String,
    val eventImage: String,
    val startDate: String
)
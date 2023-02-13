package com.gram15inch.data.remote.model.address

import com.gram15inch.data.remote.model.address.RemoteAddress
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AddressResponse(
    val code: Int,
    val isSuccess: Boolean,
    val message: String,
    @Json(name = "result")
    val result: List<RemoteAddress>
)
package com.clone.mycoupang.data.remote.model.address

data class RemoteAddress(
    val addressId: Int,
    val addressType: String,
    val detail: String,
    val name: String
)
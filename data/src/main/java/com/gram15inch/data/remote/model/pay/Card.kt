package com.clone.mycoupang.data.remote.model.pay

data class Card(
    val bankLogoImage: String,
    val bankName: String,
    val number: String,
    val paymentId: Int,
    val type: String
)
package com.gram15inch.data.datasource.remote.model.pay

data class Account(
    val bankLogoImage: String,
    val bankName: String,
    val number: String,
    val paymentId: Int,
    val type: String
)
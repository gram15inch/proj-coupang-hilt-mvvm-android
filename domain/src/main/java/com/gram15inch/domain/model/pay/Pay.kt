package com.gram15inch.domain.model.pay


/*
* type :ACCOUNT, CREDIT, COUPAY
* */
data class Pay(
    val id :Int,
    val name: String,
    val number:String,
    val type:String,
    val logo:String,
    val couPay:Int
)

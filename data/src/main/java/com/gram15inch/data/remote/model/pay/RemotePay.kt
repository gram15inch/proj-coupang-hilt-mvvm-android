package com.clone.mycoupang.data.remote.model.pay

data class RemotePay(
    val accountList: List<Account>,
    val cardList: List<Card>,
    val userCouPayMoney: Int
)
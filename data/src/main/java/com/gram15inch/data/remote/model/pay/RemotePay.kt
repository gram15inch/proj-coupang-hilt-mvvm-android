package com.gram15inch.data.remote.model.pay

import com.gram15inch.data.remote.model.pay.Account
import com.gram15inch.data.remote.model.pay.Card

data class RemotePay(
    val accountList: List<Account>,
    val cardList: List<Card>,
    val userCouPayMoney: Int
)
package com.gram15inch.domain.repository

import com.gram15inch.domain.model.pay.Pay


interface PayRepository{
    suspend fun getPayments(): List<Pay>
}


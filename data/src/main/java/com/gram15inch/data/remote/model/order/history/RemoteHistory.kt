package com.gram15inch.data.remote.model.order.history

import com.gram15inch.data.remote.model.order.history.MnO
import com.gram15inch.data.remote.model.order.history.OrderInfo

data class RemoteHistory(
    val mnO: List<MnO>,
    val orderInfo: OrderInfo
)
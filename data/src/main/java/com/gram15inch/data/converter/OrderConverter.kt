package com.gram15inch.data.converter

import com.clone.mycoupang.data.remote.model.order.history.MnO
import com.clone.mycoupang.data.remote.model.order.history.RemoteHistory
import com.clone.mycoupang.domain.model.order.history.History
import com.clone.mycoupang.domain.model.order.history.HistoryMenu

object OrderConverter {

    fun toHistory(remote:RemoteHistory ): History{
        return History(
            remote.orderInfo.orderId,
            remote.orderInfo.storeName,
            remote.orderInfo.createdAt,
            remote.orderInfo.totalPrice,
            remote.orderInfo.storeRequest,
            remote.orderInfo.riderRequest,
            remote.orderInfo.address,
            remote.mnO.map { toHistoryMenu(it) }
        )
    }

    fun toHistoryMenu(remote:MnO):HistoryMenu{
        return HistoryMenu(
            1, //todo 요청
            remote.menuName,
            remote.optName,
            remote.count
        )
    }
}

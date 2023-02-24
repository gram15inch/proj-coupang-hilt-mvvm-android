package com.gram15inch.data.converter

import com.gram15inch.data.datasource.remote.model.order.add.RemoteOrderAdd
import com.gram15inch.data.datasource.remote.model.order.history.MnO
import com.gram15inch.data.datasource.remote.model.order.history.RemoteHistory
import com.gram15inch.domain.model.order.history.History
import com.gram15inch.domain.model.order.history.HistoryMenu
import com.gram15inch.domain.model.order.history.OrderAdd

object OrderConverter {

    fun toHistory(remote: RemoteHistory): History {
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
    fun toOrderAdd(remote: RemoteOrderAdd): OrderAdd{
        return OrderAdd(
            remote.orderId
        )
    }



    fun toHistoryMenu(remote: MnO): HistoryMenu {
        return HistoryMenu(
            1, //todo 요청
            remote.menuName,
            remote.optName,
            remote.count
        )
    }
}

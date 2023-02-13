package com.gram15inch.data.repogitory

import com.gram15inch.domain.model.cart.CartMenu
import com.gram15inch.domain.model.store.StoreDetail
import com.gram15inch.domain.repository.CartRepository
import javax.inject.Inject

class CartRepositoryImpl @Inject constructor() : CartRepository {
    override var cartMenus = mutableListOf<CartMenu>()
    override val cartStore = mutableListOf<StoreDetail>()
    override val devrMsgs = arrayListOf(
        "문 앞에 두고 사진을 보내주세요(초인종 O)",
        "문 앞에 두고 사진을 보내주세요 (초인종 X)",
        "직접 받을게요 (부재 시 문 앞)",
        "문 앞에 놔주세요(초인종 X)",
        "문 앞에 놔주세요(초인종 X)",
        "도착하면 전화해주세요",
        "도착하면 문자해 주세요"
    )
}
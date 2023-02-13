package com.gram15inch.domain.model.store

import com.gram15inch.domain.model.store.DomainCategory
import com.gram15inch.domain.model.store.StoreCategory

data class StoreDetail(
    val id: Int,
    val storeName :String,
    val imgList :List<String>,
    val minFee :Int,
    val minOrder : Int,
    val score: Double,
    val scoreCnt: Int,
    val isOriginal: Boolean,
    val isCi: Boolean,
    val isBlue: Boolean,
    val isNew: Boolean,
    val isPickUp: Boolean,
    val distance: Double,
    val categoryList : List<DomainCategory>,
    val menuList : List<StoreCategory>
    )


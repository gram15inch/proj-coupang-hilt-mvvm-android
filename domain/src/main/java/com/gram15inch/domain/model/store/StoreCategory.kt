package com.clone.mycoupang.domain.model.store

data class StoreCategory(
    val id: Int,
    val title: String,
    val decrip: String,
    val menuList: List<StoreCategoryMenu>
)
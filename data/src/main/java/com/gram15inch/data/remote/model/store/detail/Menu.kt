package com.clone.mycoupang.data.remote.model.store.detail

data class Menu(
    val categoryId: Int,
    val description: String?,
    val goodCount: Int,
    val img: String?,
    val menuId: Int,
    val menuName: String,
    val orderMost: Boolean,
    val price: Int,
    val reviewMost: Boolean,
    val soldOut: Boolean
)
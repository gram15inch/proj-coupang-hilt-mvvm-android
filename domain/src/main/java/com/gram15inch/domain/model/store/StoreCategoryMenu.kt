package com.gram15inch.domain.model.store

data class StoreCategoryMenu(
    val id: Int,
    val name: String,
    val img: String,
    val descrip: String,
    val price: Int,
    val soldOut: Boolean,
    val like:Int,
    val orderMost:Boolean,
    val reviewMost: Boolean
)

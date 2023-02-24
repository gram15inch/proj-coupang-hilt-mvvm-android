package com.gram15inch.data.remote.model.store.detail

data class StoreInfo(
    val storeId:Int,
    val blue: Boolean,
    val businessHour: String,
    val cheetah: Boolean,
    val delivery: Boolean,
    val distance: Double,
    val drink: Boolean,
    val fee: Int,
    val latitude: Double,
    val longitude: Double,
    val minimum: Int,
    val new: Boolean,
    val original: Boolean,
    val score: Double,
    val scoreCnt: Int,
    val storeImg1: String,
    val storeImg2: String,
    val storeImg3: String,
    val storeName: String,
    val takeOut: Boolean
)
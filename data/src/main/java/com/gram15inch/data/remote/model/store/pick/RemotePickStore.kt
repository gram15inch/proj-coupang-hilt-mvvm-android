package com.clone.mycoupang.data.remote.model.store.pick

data class RemotePickStore(
    val avgScore: Double,
    val businessHourInfos: BusinessHourInfos,
    val deliveryFee: Int,
    val deliveryTime: String,
    val flags: Flags,
    val image1: String,
    val image2: String,
    val image3: String,
    val maxCouponInfos: MaxCouponInfos,
    val reviewCount: Int,
    val storeId: Int,
    val storeName: String,
    val userDistance: Double
)
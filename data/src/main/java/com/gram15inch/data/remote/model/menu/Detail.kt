package com.clone.mycoupang.data.remote.model.menu

data class Detail(
    val menuId:Int,
    val menuDescription: String?,
    val menuImg: String,
    val menuName: String,
    val price: Int
)
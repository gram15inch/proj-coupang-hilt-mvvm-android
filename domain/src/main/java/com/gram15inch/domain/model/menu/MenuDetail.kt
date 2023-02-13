package com.gram15inch.domain.model.menu

data class MenuDetail(
    val id: Int,
    val name: String,
    val descrip: String,
    val price: Int,
    val img: String,
    val typeList: List<MenuType>
)


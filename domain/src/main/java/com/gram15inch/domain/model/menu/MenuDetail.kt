package com.gram15inch.domain.model.menu

import kotlinx.coroutines.flow.MutableStateFlow

data class MenuDetail(
    val id: Int,
    val name: String,
    val descrip: String,
    val price: Int,
    val img: String,
    val typeList: List<MenuType>
)

data class MenuType(
    val id: Int,
    val Name: String,
    val multiple: Boolean,
    val necessary: Boolean,
    val optList: List<MenuTypeOption>
)

data class MenuTypeOption(
    val id: Int,
    val typeId:Int,
    val Name: String,
    val price : Int
)

data class SelectedMenuType(
    val id: Int,
    val Name: String,
    val multiple: Boolean,
    val necessary: Boolean,
    val optList: MutableStateFlow<List<MenuTypeOption>>
)
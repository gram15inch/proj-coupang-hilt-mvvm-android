package com.gram15inch.domain.model.menu

data class MenuType(
    val id: Int,
    val Name: String,
    val multiple: Boolean,
    val necessary: Boolean,
    val optList: List<MenuTypeOption>
)
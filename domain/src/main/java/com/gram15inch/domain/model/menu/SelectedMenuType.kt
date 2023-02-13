package com.gram15inch.domain.model.menu

import kotlinx.coroutines.flow.MutableStateFlow

data class SelectedMenuType(
    val id: Int,
    val Name: String,
    val multiple: Boolean,
    val necessary: Boolean,
    val optList: MutableStateFlow<List<MenuTypeOption>>
)
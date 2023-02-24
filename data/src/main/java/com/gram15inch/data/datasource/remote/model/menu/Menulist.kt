package com.gram15inch.data.datasource.remote.model.menu

data class Menulist(
    val categoryName: String,
    val multiple: Boolean,
    val necessary: Boolean,
    val optId: Int,
    val optList: List<com.gram15inch.data.datasource.remote.model.menu.Opt>
)
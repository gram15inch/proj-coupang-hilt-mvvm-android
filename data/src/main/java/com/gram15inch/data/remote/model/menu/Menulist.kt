package com.clone.mycoupang.data.remote.model.menu

data class Menulist(
    val categoryName: String,
    val multiple: Boolean,
    val necessary: Boolean,
    val optId: Int,
    val optList: List<Opt>
)
package com.gram15inch.data.remote.model.menu

import com.gram15inch.data.remote.model.menu.Detail
import com.gram15inch.data.remote.model.menu.Menulist

data class RemoteMenuDetail(
    val detail: Detail,
    val menulist: List<Menulist>
)
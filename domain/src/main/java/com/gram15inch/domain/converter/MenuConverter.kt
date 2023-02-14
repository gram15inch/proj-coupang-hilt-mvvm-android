package com.gram15inch.domain.converter

import com.gram15inch.domain.model.menu.MenuType
import com.gram15inch.domain.model.menu.SelectedMenuType


object MenuConverter{
    fun toMenuType(remote: SelectedMenuType): MenuType {
        return  MenuType(
            remote.id,
            remote.Name,
            remote.multiple,
            remote.necessary,
            remote.optList.value
        )
    }
}

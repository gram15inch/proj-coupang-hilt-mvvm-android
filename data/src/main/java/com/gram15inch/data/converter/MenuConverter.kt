package com.gram15inch.data.converter

import com.gram15inch.data.remote.model.menu.Menulist
import com.gram15inch.data.remote.model.menu.Opt
import com.gram15inch.data.remote.model.menu.RemoteMenuDetail
import com.gram15inch.domain.model.menu.MenuDetail
import com.gram15inch.domain.model.menu.MenuType
import com.gram15inch.domain.model.menu.MenuTypeOption
import com.gram15inch.domain.model.menu.SelectedMenuType

object MenuConverter {
    fun toMenuDetail(remote: RemoteMenuDetail): MenuDetail {
        return MenuDetail(
            remote.detail.menuId,
            remote.detail.menuName,
            remote.detail.menuDescription?:"",
            remote.detail.price,
            remote.detail.menuImg,
            remote.menulist.map { toMenuType(it) }
        )
    }

    fun toMenuType(remote: Menulist): MenuType {
        return MenuType(
            id=remote.optId,
            Name=remote.categoryName,
            multiple=remote.multiple,
            necessary= remote.necessary,
            optList= remote.optList.map { toMenuTypeOption(it) }
        )
    }

    fun toMenuType(remote: SelectedMenuType): MenuType {
        return  MenuType(
            remote.id,
            remote.Name,
            remote.multiple,
            remote.necessary,
            remote.optList.value
        )
    }
    fun toMenuTypeOption(remote: Opt): MenuTypeOption {
        return MenuTypeOption(
            id = remote.optId,
            typeId = remote.categoryID,
            Name = remote.optName,
            price = remote.optPrice
        )
    }
}



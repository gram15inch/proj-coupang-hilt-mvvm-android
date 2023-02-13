package com.gram15inch.data.converter

import com.gram15inch.data.remote.model.store.category.RemoteHomeCategory
import com.gram15inch.data.remote.model.store.detail.Category
import com.gram15inch.data.remote.model.store.detail.Menu
import com.gram15inch.data.remote.model.store.detail.RemoteStoreDetail
import com.gram15inch.data.remote.model.store.pick.RemotePickStore
import com.gram15inch.domain.model.store.*
import com.gram15inch.domain.model.store.home.HomeCategory


object StoreConverter {
    fun toStore(remote: RemotePickStore): Store {

        return Store(
            id = remote.storeId,
            storeName = remote.storeName,
            tag = "ci",
            star = remote.avgScore.toFloat(),
            reviewCount = remote.reviewCount,
            distance = remote.userDistance.toFloat(),
            fee = remote.deliveryFee,
            imgs = listOf(remote.image1, remote.image2, remote.image3),
            isPackage = remote.flags.canTakeOut,
            isNew = remote.flags.newStore,
            isCi = remote.flags.cheetah,
            isBlue = remote.flags.blueRibbon,
            coupon = remote.maxCouponInfos.maxCouponPrice ?: 0,
            deliveryTime = remote.deliveryTime,
        )
    }

    fun toStoreDetail(remote: RemoteStoreDetail): StoreDetail {

        return StoreDetail(
            id = remote.storeInfo.storeId,
            storeName = remote.storeInfo.storeName,
            imgList = listOf(
                remote.storeInfo.storeImg1,
                remote.storeInfo.storeImg2,
                remote.storeInfo.storeImg3,
            ),
            minFee = remote.storeInfo.fee,
            minOrder = remote.storeInfo.minimum,
            score = remote.storeInfo.score,
            scoreCnt = remote.storeInfo.scoreCnt,
            isOriginal = remote.storeInfo.original,
            isCi = remote.storeInfo.cheetah,
            isBlue = remote.storeInfo.blue,
            isNew = remote.storeInfo.new,
            isPickUp = remote.storeInfo.takeOut,
            distance = remote.storeInfo.distance,
            categoryList = remote.categoryList.map { toDomainCategory(it) },
            menuList = remote.categoryList.run {
                var mIdx = 0
                this.map {
                    toStoreCategory(
                        category = it,
                        remoteMenuList = remote.menuList[mIdx++]
                    )
                }
            }
        )
    }

    fun toDomainCategory(remote: Category) =
        DomainCategory(
            id = remote.categoryId,
            name = remote.categoryName
        )

    fun toStoreCategory(category: Category, remoteMenuList: List<Menu>) =
        StoreCategory(
            id = category.categoryId,
            title = category.categoryName,
            decrip = "메뉴 사진은 연출된 이미지로 실제 조리된 음식과 다를 수 있습니다.",
            menuList = remoteMenuList.map { toStoreCategoryMenu(it) }
        )

    fun toStoreCategoryMenu(remote: Menu) = StoreCategoryMenu(
        id= remote.menuId,
        name = remote.menuName,
        img = remote.img?:"",
        descrip = remote.description?:"",
        price = remote.price,
        soldOut = remote.soldOut,
        remote.goodCount,
        remote.orderMost,
        remote.reviewMost,
    )

    var hcIdx=0
    fun toHomeCategory(remote: RemoteHomeCategory): HomeCategory {
        return HomeCategory(
            hcIdx++, //todo 요청하기
           remote.categoryImg,
           remote.categoryName
        )
    }


}

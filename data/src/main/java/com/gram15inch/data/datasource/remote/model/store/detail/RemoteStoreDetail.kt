package com.gram15inch.data.remote.model.store.detail

data class RemoteStoreDetail(
    val categoryList: List<Category>,
    val menuList: List<List<Menu>>,
    val storeInfo: StoreInfo
)
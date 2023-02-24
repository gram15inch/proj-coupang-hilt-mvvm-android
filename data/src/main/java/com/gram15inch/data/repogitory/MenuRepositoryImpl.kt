package com.gram15inch.data.repogitory

import com.gram15inch.data.converter.MenuConverter
import com.gram15inch.data.datasource.remote.MenuRemoteDataSource
import com.gram15inch.data.datasource.remote.apiservice.MenuApiService
import com.gram15inch.domain.model.menu.MenuDetail
import com.gram15inch.domain.repository.MenuRepository
import javax.inject.Inject


class MenuRepositoryImpl @Inject constructor(private val menuRemoteDataSource: MenuRemoteDataSource) :
    MenuRepository {
    override suspend fun getMenu(storeId: Int, menuId: Int): MenuDetail? {
        menuRemoteDataSource.getMenuResponse(storeId, menuId).also {
            if (it.isSuccess)
                if (it.result != null)
                    return MenuConverter.toMenuDetail(it.result)
        }
        return null
    }

}
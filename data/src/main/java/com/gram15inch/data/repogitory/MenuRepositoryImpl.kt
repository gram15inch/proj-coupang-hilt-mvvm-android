package com.gram15inch.data.repogitory

import com.gram15inch.data.converter.EventConverter
import com.gram15inch.data.converter.MenuConverter
import com.gram15inch.data.remote.MenuApiService
import com.gram15inch.data.remote.model.menu.MenuDetailResponse
import com.gram15inch.domain.model.menu.MenuDetail
import com.gram15inch.domain.repository.MenuRepository
import retrofit2.Response
import javax.inject.Inject


class MenuRepositoryImpl @Inject constructor(private val menuApiService: MenuApiService) :
    MenuRepository {
    override suspend fun getMenu(storeId: Int, menuId: Int): MenuDetail? {
        menuApiService.getMenu(storeId, menuId).body()?.also {
            if (it.isSuccess)
                if (it.result != null)
                    return MenuConverter.toMenuDetail(it.result)
        }
        return null
    }

}
package com.gram15inch.data.datasource.remote

import com.gram15inch.data.datasource.remote.model.address.AddressResponse
import com.gram15inch.domain.model.address.Address

interface AddressRemoteDataSource {
   suspend fun getAddressResponse(): AddressResponse
}
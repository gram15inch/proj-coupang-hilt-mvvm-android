package com.gram15inch.domain.repository

import com.gram15inch.domain.model.address.Address


interface AddressRepository {
    suspend fun getAddress():List<Address>
}


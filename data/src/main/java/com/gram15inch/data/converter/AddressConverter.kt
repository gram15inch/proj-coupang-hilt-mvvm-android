package com.gram15inch.data.converter

import com.gram15inch.data.datasource.remote.model.address.RemoteAddress
import com.gram15inch.domain.model.address.Address

object AddressConverter {
    fun toAddress(remote: RemoteAddress): Address {
        return Address(
            remote.addressId,
            remote.name,
            remote.detail,
            remote.addressType,
        )
    }
}

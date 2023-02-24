package com.gram15inch.data.di

import com.gram15inch.data.datasource.remote.*
import com.gram15inch.data.datasource.remote.impl.*
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {

    @Binds
    abstract fun bindAddressRemoteDataSource(impl: AddressRemoteDataSourceImpl)
            : AddressRemoteDataSource
    
    @Binds
    abstract fun bindCartRemoteDataSource(impl: CartRemoteDataSourceImpl)
            : CartRemoteDataSource
    
    @Binds
    abstract fun bindEventRemoteDataSource(impl: EventRemoteDataSourceImpl)
            : EventRemoteDataSource
    
    @Binds
    abstract fun bindMenuRemoteDataSource(impl: MenuRemoteDataSourceImpl)
            : MenuRemoteDataSource
    
    @Binds
    abstract fun bindOrderRemoteDataSource(impl: OrderRemoteDataSourceImpl)
            : OrderRemoteDataSource
    
    @Binds
    abstract fun bindPayRemoteDataSource(impl: PayRemoteDataSourceImpl)
            : PayRemoteDataSource
      
    @Binds
    abstract fun bindStoreRemoteDataSource(impl: StoreRemoteDataSourceImpl)
            : StoreRemoteDataSource
      
    @Binds
    abstract fun bindUserRemoteDataSource(impl: UserRemoteDataSourceImpl)
            : UserRemoteDataSource

}
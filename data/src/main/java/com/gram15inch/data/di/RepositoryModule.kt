package com.gram15inch.data.di

import com.gram15inch.data.repogitory.*
import com.gram15inch.domain.repository.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule{

    @Provides
    fun provideAddressRepository(repository: AddressRepositoryImpl): AddressRepository {
        return repository
    }
    @Provides
    fun provideCartRepository(repository: CartRepositoryImpl): CartRepository {
        return repository
    }
    @Provides
    fun provideEventRepository(repository: EventRepositoryImpl): EventRepository {
        return repository
    }
    @Provides
    fun provideMenuRepository(repository: MenuRepositoryImpl): MenuRepository {
        return repository
    }
    @Provides
    fun provideOrderRepository(repository: OrderRepositoryImpl): OrderRepository {
        return repository
    }
    @Provides
    fun providePayRepository(repository: PayRepositoryImpl): PayRepository {
        return repository
    }
    @Provides
    fun provideStoreRepository(repository: StoreRepositoryImpl): StoreRepository {
        return repository
    }
    @Provides
    fun provideUserRepository(repository: UserRepositoryImpl): UserRepository {
        return repository
    }


}

package com.gram15inch.data.di

import com.gram15inch.data.datasource.remote.apiservice.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object ApiServiceModule {


    @Provides
    fun provideUserApiService(retrofit: Retrofit): UserApiService {
        return retrofit.create(UserApiService::class.java)
    }

    @Provides
    fun provideAddressApiService(retrofit: Retrofit): AddressApiService {
        return retrofit.create(AddressApiService::class.java)
    }

    @Provides
    fun providePayApiService(retrofit: Retrofit): PayApiService {
        return retrofit.create(PayApiService::class.java)
    }

    @Provides
    fun provideStoreApiService(retrofit: Retrofit): StoreApiService {
        return retrofit.create(StoreApiService::class.java)
    }

    @Provides
    fun provideMenuApiService(retrofit: Retrofit): MenuApiService {
        return retrofit.create(MenuApiService::class.java)
    }

    @Provides
    fun provideEventApiService(retrofit: Retrofit): EventApiService {
        return  retrofit.create(EventApiService::class.java)
    }
    @Provides
    fun provideOrderApiService(retrofit: Retrofit): OrderApiService {
        return  retrofit.create(OrderApiService::class.java)
    }

}
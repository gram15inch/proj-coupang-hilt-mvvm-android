package com.gram15inch.data.di



import com.clone.mycoupang.data.NullToEmptyStringAdapter
import com.gram15inch.data.XAccessTokenInterceptor
import com.clone.mycoupang.data.remote.*
import com.gram15inch.data.remote.*
import com.gram15inch.data.repogitory.CartRepositoryImpl
import com.gram15inch.domain.policy.NetworkPolicy
import com.gram15inch.domain.repository.CartRepository
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RetrofitClientModule {

    @Singleton
    @Provides
    fun provideClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .readTimeout(5000, TimeUnit.MILLISECONDS)
            .connectTimeout(5000, TimeUnit.MILLISECONDS)
            // 로그캣에 okhttp.OkHttpClient로 검색하면 http 통신 내용을 보여줍니다.
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .addNetworkInterceptor(XAccessTokenInterceptor()) // JWT 자동 헤더 전송
            .build()
    }

    @Singleton
    @Provides
    fun provideMoshi(): Moshi {
        return Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .add(NullToEmptyStringAdapter())
            .build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(moshi: Moshi, client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(
                MoshiConverterFactory.create(moshi)
            )
            .client(client)
            .baseUrl(NetworkPolicy.BASE_URL)
            .build()
    }

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

    @Singleton
    @Provides
    fun provideCartDataSource(): CartRepository {
        return CartRepositoryImpl()
    }


}
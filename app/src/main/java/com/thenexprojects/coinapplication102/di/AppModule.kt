package com.thenexprojects.coinapplication102.di

import com.thenexprojects.coinapplication102.common.Constant
import com.thenexprojects.coinapplication102.data.api.WebApiService
import com.thenexprojects.coinapplication102.data.repository.CoinsRepositoryImpl
import com.thenexprojects.coinapplication102.domain.repository.CoinsRepository
import com.thenexprojects.coinapplication102.domain.use_case.GetCoinsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

//    @Provides
//    @Singleton
//    fun getRepository(repositoryImpl: CoinsRepositoryImpl):CoinsRepository


    @Provides
    @Singleton
    fun provideCoinsRepository(coinsRepositoryImpl: CoinsRepositoryImpl): CoinsRepository {
        return coinsRepositoryImpl
    }

    @Provides
    @Singleton
    fun provideGetCoinUseCase(coinsRepository: CoinsRepository): GetCoinsUseCase {
        return GetCoinsUseCase(coinsRepository)
    }

    @Provides
    @Singleton
    fun getApiService(retrofit: Retrofit): WebApiService {
        return retrofit.create(WebApiService::class.java)
    }

    @Provides
    @Singleton
    fun getRetrofit(): Retrofit {

        val loggingInterceptor= HttpLoggingInterceptor()
        loggingInterceptor.level= HttpLoggingInterceptor.Level.BODY

        val clientBuilder= OkHttpClient.Builder().addInterceptor(loggingInterceptor).build()

        return Retrofit.Builder().baseUrl(Constant.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .client(clientBuilder)
            .build()
    }


}
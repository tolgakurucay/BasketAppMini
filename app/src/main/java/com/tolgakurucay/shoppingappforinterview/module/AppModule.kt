package com.tolgakurucay.shoppingappforinterview.module

import com.tolgakurucay.shoppingappforinterview.service.OrderAPI
import com.tolgakurucay.shoppingappforinterview.service.OrderConstants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun injectAPI() : OrderAPI{
        return Retrofit.Builder()
            .baseUrl(OrderConstants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(OrderAPI::class.java)
    }
}
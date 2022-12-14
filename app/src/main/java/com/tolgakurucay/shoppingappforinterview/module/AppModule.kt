package com.tolgakurucay.shoppingappforinterview.module

import com.tolgakurucay.shoppingappforinterview.repository.CaseBasketRepository
import com.tolgakurucay.shoppingappforinterview.repository.CaseBasketRepositoryInterface
import com.tolgakurucay.shoppingappforinterview.repository.CaseListingRepository
import com.tolgakurucay.shoppingappforinterview.repository.CaseListingRepositoryInterface
import com.tolgakurucay.shoppingappforinterview.service.OrderAPI
import com.tolgakurucay.shoppingappforinterview.service.OrderConstants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
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

    @Provides
    @Singleton
    fun injectBasketInterface() : CaseBasketRepositoryInterface{
        return CaseBasketRepository(injectAPI())
    }

    @Provides
    @Singleton
    fun injectListInterface() : CaseListingRepositoryInterface{
        return CaseListingRepository(injectAPI())
    }


}
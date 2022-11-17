package com.tolgakurucay.shoppingappforinterview.repository

import com.tolgakurucay.shoppingappforinterview.model.GetModel
import com.tolgakurucay.shoppingappforinterview.model.PostModel
import com.tolgakurucay.shoppingappforinterview.utils.Resource
import dagger.Binds
import dagger.Component
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.internal.managers.ApplicationComponentManager
import dagger.hilt.components.SingletonComponent
import retrofit2.http.Body
import javax.inject.Inject
import javax.inject.Singleton


interface CaseBasketRepositoryInterface {

    suspend fun placeOrder(postModel : List<PostModel>): Resource<GetModel>
}



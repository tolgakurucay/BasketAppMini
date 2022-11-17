package com.tolgakurucay.shoppingappforinterview.repository

import com.tolgakurucay.shoppingappforinterview.model.ListModel
import com.tolgakurucay.shoppingappforinterview.utils.Resource
import dagger.Component
import dagger.Provides
import javax.inject.Inject


interface CaseListingRepositoryInterface {

    suspend fun getItems() : Resource<List<ListModel>>

}
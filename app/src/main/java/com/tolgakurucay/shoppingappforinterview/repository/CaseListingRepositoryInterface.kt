package com.tolgakurucay.shoppingappforinterview.repository

import com.tolgakurucay.shoppingappforinterview.model.ListModel
import com.tolgakurucay.shoppingappforinterview.utils.Resource


interface CaseListingRepositoryInterface {

    suspend fun getItems() : Resource<List<ListModel>>

}
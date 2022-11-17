package com.tolgakurucay.shoppingappforinterview.repository

import com.tolgakurucay.shoppingappforinterview.model.ListModel
import com.tolgakurucay.shoppingappforinterview.utils.Resource

class CaseListingRepositoryFake : CaseListingRepositoryInterface {
    override suspend fun getItems(): Resource<List<ListModel>> {
        val model = ArrayList<ListModel>()
            return Resource.success(model.toList())
    }

}
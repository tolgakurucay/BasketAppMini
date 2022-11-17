package com.tolgakurucay.shoppingappforinterview.repository

import com.tolgakurucay.shoppingappforinterview.model.ListModel
import com.tolgakurucay.shoppingappforinterview.utils.Resource

class CaseListingRepositoryFake : CaseListingRepositoryInterface {
    override suspend fun getItems(): Resource<List<ListModel>> {
        TODO("Not yet implemented")
    }

}
package com.tolgakurucay.shoppingappforinterview.repository

import com.tolgakurucay.shoppingappforinterview.model.GetModel
import com.tolgakurucay.shoppingappforinterview.model.PostModel
import com.tolgakurucay.shoppingappforinterview.utils.Resource

class CaseBasketRepositoryFake : CaseBasketRepositoryInterface {

    override suspend fun placeOrder(postModel: List<PostModel>): Resource<GetModel> {
        return Resource.success(GetModel("success","order received"))
    }
}
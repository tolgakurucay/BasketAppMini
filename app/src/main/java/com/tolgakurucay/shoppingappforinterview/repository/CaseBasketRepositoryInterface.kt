package com.tolgakurucay.shoppingappforinterview.repository

import com.tolgakurucay.shoppingappforinterview.model.GetModel
import com.tolgakurucay.shoppingappforinterview.model.PostModel
import com.tolgakurucay.shoppingappforinterview.utils.Resource

interface CaseBasketRepositoryInterface {

    suspend fun placeOrder(postModel : PostModel): Resource<GetModel>
}
package com.tolgakurucay.shoppingappforinterview.repository

import com.tolgakurucay.shoppingappforinterview.model.GetModel
import com.tolgakurucay.shoppingappforinterview.model.PostModel
import com.tolgakurucay.shoppingappforinterview.service.OrderAPI
import com.tolgakurucay.shoppingappforinterview.utils.Resource
import retrofit2.awaitResponse
import retrofit2.http.Body
import javax.inject.Inject

class CaseBasketRepository @Inject constructor(
    private val orderApi: OrderAPI
) : CaseBasketRepositoryInterface {
    override suspend fun placeOrder(postModel : List<PostModel>): Resource<GetModel> {

        return try{
            val response = orderApi.placeOrder(postModel).awaitResponse()
            if(response.isSuccessful){
                response.body()?.let {
                    return@let Resource.success(it)
                } ?: Resource.error("ERROR11",null)
            }
            else{
                return Resource.error("ERROR22",null)
            }
        }
        catch (ex : Exception){
            return Resource.error("Error33",null)
        }

    }


}
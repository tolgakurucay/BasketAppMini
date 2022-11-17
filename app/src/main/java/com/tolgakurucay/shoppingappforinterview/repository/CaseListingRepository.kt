package com.tolgakurucay.shoppingappforinterview.repository

import android.util.Log
import com.tolgakurucay.shoppingappforinterview.model.ListModel
import com.tolgakurucay.shoppingappforinterview.service.OrderAPI
import com.tolgakurucay.shoppingappforinterview.utils.Resource
import retrofit2.awaitResponse
import javax.inject.Inject

class CaseListingRepository @Inject constructor(
    private val orderApi: OrderAPI
): CaseListingRepositoryInterface {
    override suspend fun getItems(): Resource<List<ListModel>> {

        val TAG = "bilgi"
        return try{
            val response = orderApi.getItems().awaitResponse()

            if(response.isSuccessful){
                response.body()?.let {
                    return@let Resource.success(it)
                } ?: Resource.error("ERROR",null)
            }
            else{
                return Resource.error("ERROR",null)
            }
        }
        catch (ex : Exception){
          //  Log.d(TAG, "getItems:${ex.message.toString()} ")
            return Resource.error("ERROR",null)

        }


    }

}
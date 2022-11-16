package com.tolgakurucay.shoppingappforinterview.service

import com.tolgakurucay.shoppingappforinterview.model.GetModel
import com.tolgakurucay.shoppingappforinterview.model.ListModel
import com.tolgakurucay.shoppingappforinterview.model.PostModel
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface OrderAPI {

    @Headers("Content-Type: application/json")
    @GET(OrderConstants.GET_ENDPOINT)
    fun getItems() : Call<List<ListModel>>

    @Headers("Content-Type: application/json")
    @POST(OrderConstants.POST_ENDPOINT)
    fun placeOrder(@Body postmodel : List<PostModel>) : Call<GetModel>

}
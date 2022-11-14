package com.tolgakurucay.shoppingappforinterview.model

import com.google.gson.annotations.SerializedName

data class ListModel(

    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("price")
    val price: String,
    @SerializedName("currency")
    val currency: String,
    @SerializedName("image")
    val imagePath: String,
    val itemCount : Int = 0
)

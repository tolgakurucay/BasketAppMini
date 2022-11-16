package com.tolgakurucay.shoppingappforinterview.model

import com.google.gson.annotations.SerializedName

data class PostModel(
    @SerializedName("id")
    val id : Int,
    @SerializedName("amount")
    val amount : Int
)

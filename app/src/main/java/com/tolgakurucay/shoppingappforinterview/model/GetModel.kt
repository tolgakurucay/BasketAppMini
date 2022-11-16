package com.tolgakurucay.shoppingappforinterview.model

import com.google.gson.annotations.SerializedName

data class GetModel(
    @SerializedName("status")
    val status : String,
    @SerializedName("message")
    val message : String
)

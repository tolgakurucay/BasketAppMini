package com.tolgakurucay.shoppingappforinterview.model

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class GetModelTest {


    @Test
    fun createObjectTest(){
        val getModel = GetModel("404","Object not found")
        assertThat(getModel).isInstanceOf(GetModel::class.java)
    }
}
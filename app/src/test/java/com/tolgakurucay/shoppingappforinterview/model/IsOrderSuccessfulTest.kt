package com.tolgakurucay.shoppingappforinterview.model

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class IsOrderSuccessfulTest{



    @Test
    fun createObjectTest(){
        val isOrderSuccessful = IsOrderSuccessful(2,"name",false)
        assertThat(isOrderSuccessful).isInstanceOf(IsOrderSuccessful::class.java)
    }

}
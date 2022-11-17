package com.tolgakurucay.shoppingappforinterview.application

import android.app.Application
import com.google.common.truth.Truth.assertThat
import org.junit.Test

class ApplicationClassTest {

    @Test
    fun isApplicationClassAnInstanceOfApplicationClass(){
        val applicationClass = ApplicationClass()
        assertThat(applicationClass).isInstanceOf(Application::class.java)
    }
}
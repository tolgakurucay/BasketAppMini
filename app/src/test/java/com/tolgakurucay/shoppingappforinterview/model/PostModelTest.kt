package com.tolgakurucay.shoppingappforinterview.model

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class PostModelTest {


    @Test
    fun createObjectTest(){
        val postModel = PostModel(1,5)
        assertThat(postModel).isInstanceOf(PostModel::class.java)
    }
}
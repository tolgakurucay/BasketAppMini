package com.tolgakurucay.shoppingappforinterview.model

import com.google.common.truth.Truth.assertThat
import org.junit.Test


class ListModelTest {




    @Test
    fun createListModelWithoutCountTest(){
        val listModel = ListModel(1,"name","30","USD","www.asd.co")
        assertThat(listModel).isInstanceOf(ListModel::class.java)
    }

    @Test
    fun createListModel(){
        val listModel = ListModel(1,"name","30","USD","www.asd.co",6)
        assertThat(listModel).isInstanceOf(ListModel::class.java)
    }

    @Test
    fun `create list model without itemCount and itemCount returns 1`(){
        val listModel = ListModel(1,"name","30","USD","www.asd.co",)
        assertThat(listModel.itemCount).isEqualTo(1)
    }




}
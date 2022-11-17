package com.tolgakurucay.shoppingappforinterview.service

import com.google.common.truth.Truth.assertThat
import com.tolgakurucay.shoppingappforinterview.model.ListModel
import org.junit.After
import org.junit.Before
import org.junit.Test

class TempSaveTest {

    private lateinit var tempSaveObject : TempSave

    @Before
    fun setup(){
        tempSaveObject = TempSave
    }


    @Test
    fun addListModelTest(){
        tempSaveObject.removeAll()
        val exampleListModel = ListModel(1,"name","100","TL","www.example.com",2)
        tempSaveObject.addListModel(exampleListModel)
        val listModelThatAddedModel=tempSaveObject.getListModel()
        assertThat(listModelThatAddedModel).contains(exampleListModel)
    }

    @Test
    fun removeListModelTest(){
        tempSaveObject.removeAll()
        val exampleListModel = ListModel(1,"name","100","TL","www.example.com",2)
        tempSaveObject.addListModel(exampleListModel)
        tempSaveObject.removeListModel(exampleListModel)
        val listModel=tempSaveObject.getListModel()
        assertThat(listModel).doesNotContain(exampleListModel)
    }

    @Test
    fun removeAllTest(){
        val exampleListModel = ListModel(1,"name","100","TL","www.example.com",2)
        val exampleListModel1 = ListModel(1,"name","100","TL","www.example.com",3)
        tempSaveObject.addListModel(exampleListModel)
        tempSaveObject.addListModel(exampleListModel1)

        tempSaveObject.removeAll()
        val listModel = tempSaveObject.getListModel()
        assertThat(listModel).isEmpty()
    }

    @Test
    fun `Get list model is object is instance of ArrayList of ListModel`(){
        val listModel = tempSaveObject.getListModel()
        assertThat(listModel).isInstanceOf(ArrayList<ListModel>()::class.java)
    }


    @After
    fun tearDown(){
        //Do when test has finished
    }
}
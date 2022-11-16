package com.tolgakurucay.shoppingappforinterview.service

import com.tolgakurucay.shoppingappforinterview.model.ListModel

object TempSave {

    private val listModelArrayList = ArrayList<ListModel>()

    fun addListModel(listModel: ListModel) = listModelArrayList.add(listModel)
    fun removeListModel(listModel: ListModel) = listModelArrayList.remove(listModel)
    fun removeAll() = listModelArrayList.clear()
    fun getListModel() : ArrayList<ListModel> = listModelArrayList


}
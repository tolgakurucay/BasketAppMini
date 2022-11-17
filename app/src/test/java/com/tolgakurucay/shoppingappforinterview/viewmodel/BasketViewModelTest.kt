@file:OptIn(ExperimentalCoroutinesApi::class)

package com.tolgakurucay.shoppingappforinterview.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import com.tolgakurucay.shoppingappforinterview.getOrAwaitValueTest
import com.tolgakurucay.shoppingappforinterview.model.GetModel
import com.tolgakurucay.shoppingappforinterview.model.IsOrderSuccessful
import com.tolgakurucay.shoppingappforinterview.model.ListModel
import com.tolgakurucay.shoppingappforinterview.repository.CaseBasketRepositoryFake
import com.tolgakurucay.shoppingappforinterview.service.MainCoroutineRule
import com.tolgakurucay.shoppingappforinterview.utils.Resource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class BasketViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    private lateinit var viewmodel : BasketViewModel

    @Before
    fun setup(){
        viewmodel = BasketViewModel(CaseBasketRepositoryFake())

    }


    @Test
    fun `placeOrderInViewModel returns ListIsOrderSuccessfulList`() = runTest{
        val listModel = ArrayList<ListModel>()
        listModel.add(ListModel(1,"name","300","TL","www.random.com"))
        viewmodel.placeOrderInViewModel(listModel)
        advanceUntilIdle()


        val isOrderSuccessfulList = ArrayList<IsOrderSuccessful>()
        isOrderSuccessfulList.add(IsOrderSuccessful(1,"name",true))
        val response = Resource.success(isOrderSuccessfulList.toList())
        assertThat(viewmodel.isOrderSuccessfulLiveData.getOrAwaitValueTest()).isEqualTo(response)


    }
}
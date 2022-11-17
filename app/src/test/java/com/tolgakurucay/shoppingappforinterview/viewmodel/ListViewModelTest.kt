package com.tolgakurucay.shoppingappforinterview.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import com.tolgakurucay.shoppingappforinterview.getOrAwaitValueTest
import com.tolgakurucay.shoppingappforinterview.module.AppModule
import com.tolgakurucay.shoppingappforinterview.repository.CaseBasketRepositoryFake
import com.tolgakurucay.shoppingappforinterview.repository.CaseListingRepository
import com.tolgakurucay.shoppingappforinterview.repository.CaseListingRepositoryFake
import com.tolgakurucay.shoppingappforinterview.service.MainCoroutineRule
import com.tolgakurucay.shoppingappforinterview.utils.Resource
import com.tolgakurucay.shoppingappforinterview.utils.Status
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.runTest

import org.junit.Before
import org.junit.Rule
import org.junit.Test


@ExperimentalCoroutinesApi
class ListViewModelTest {

    @get:Rule
    var instantTaskExecuterRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private lateinit var viewModel : ListViewModel

    @Before
    fun setup(){

        viewModel = ListViewModel(CaseListingRepositoryFake())
    }


    @Test
    fun func1()= runTest{

        viewModel.getItemsInViewModel()
        advanceUntilIdle()
       // viewModel.resetListModel()
        assertThat(viewModel.listModelLiveData.getOrAwaitValueTest()).isEqualTo(Status.SUCCESS)


    }

    @Test
    fun loadingLiveDataTest(){
       /* viewModel.getItems()
        val loadingLiveData = viewModel.loadingLiveData.getOrAwaitValueTest()
        assertThat(loadingLiveData).isAnyOf(true,false)*/
    }


    @Test
    fun errorLiveDataTest(){
      /*  viewModel.getItems()
        val errorLiveData = viewModel.errorLiveData.getOrAwaitValueTest(1)*/

    }





}
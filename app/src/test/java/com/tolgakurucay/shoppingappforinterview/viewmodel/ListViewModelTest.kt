package com.tolgakurucay.shoppingappforinterview.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.google.common.truth.Truth.assertThat
import com.tolgakurucay.shoppingappforinterview.getOrAwaitValueTest
import com.tolgakurucay.shoppingappforinterview.model.ListModel
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
    fun `get items in viewmodel returns success`()= runTest{

        viewModel.getItemsInViewModel()
        advanceUntilIdle()
        assertThat(viewModel.listModelLiveData.getOrAwaitValueTest().status).isEqualTo(Status.SUCCESS)

    }










}
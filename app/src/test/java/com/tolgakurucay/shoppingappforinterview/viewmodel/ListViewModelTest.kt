package com.tolgakurucay.shoppingappforinterview.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import com.google.common.truth.Truth.assertThat
import com.tolgakurucay.shoppingappforinterview.getOrAwaitValueTest
import com.tolgakurucay.shoppingappforinterview.model.ListModel
import com.tolgakurucay.shoppingappforinterview.repository.CaseListingRepository
import com.tolgakurucay.shoppingappforinterview.service.MainCoroutineRule

import org.junit.Before
import org.junit.Rule
import org.junit.Test

class FragmentCaseListViewModelTest {

    @get:Rule
    var instantTaskExecuterRule = InstantTaskExecutorRule()

    private lateinit var viewModel : ListViewModel

    @Before
    fun setup(){

    }


    @Test
    fun func1(){



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
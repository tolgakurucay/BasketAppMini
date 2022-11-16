package com.tolgakurucay.shoppingappforinterview.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tolgakurucay.shoppingappforinterview.model.ListModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import com.tolgakurucay.shoppingappforinterview.module.AppModule
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@HiltViewModel
class FragmentCaseListingViewModel @Inject constructor(): ViewModel() {
    
    private val TAG = "bilgi"

    //Models
    private val listModelMutableLiveData = MutableLiveData<List<ListModel>>()
    val listModelLiveData : LiveData<List<ListModel>>
    get() = listModelMutableLiveData

    //Error message
    private val errorMutable = MutableLiveData<String>()
    val errorLiveData :  LiveData<String>
    get() = errorMutable

    //Loading message
    private val loadingMutable = MutableLiveData<Boolean>()
    val loadingLiveData : LiveData<Boolean>
    get() = loadingMutable

    fun getItems(){
        loadingMutable.value=true
        val getApi = AppModule.injectAPI()
        getApi.getItems().enqueue(object : Callback<List<ListModel>>{
            override fun onResponse(
                call: Call<List<ListModel>>,
                response: Response<List<ListModel>>
            ) {
                if(response.isSuccessful){

                    listModelMutableLiveData.value = response.body()
                    loadingMutable.value=false
                }
                else{
                    loadingMutable.value=false
                    //This field will support multi language
                    errorMutable.value="An error occured"
                }
            }

            override fun onFailure(call: Call<List<ListModel>>, t: Throwable) {
                errorMutable.value=t.localizedMessage
                loadingMutable.value=false
            }

        })
    }
}
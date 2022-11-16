package com.tolgakurucay.shoppingappforinterview.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tolgakurucay.shoppingappforinterview.model.GetModel
import com.tolgakurucay.shoppingappforinterview.model.IsOrderSuccessful
import com.tolgakurucay.shoppingappforinterview.model.ListModel
import com.tolgakurucay.shoppingappforinterview.model.PostModel
import com.tolgakurucay.shoppingappforinterview.module.AppModule
import com.tolgakurucay.shoppingappforinterview.service.TempSave
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class FragmentCaseBasketViewModel @Inject constructor(): ViewModel() {

    private val TAG = "bilgi"

    //Loading
    private val loadingMutable = MutableLiveData<Boolean>()
    val loadingLiveData : LiveData<Boolean>
    get() = loadingMutable

    //Error
    private val errorMutable = MutableLiveData<String>()
    val errorLiveData : LiveData<String>
        get() = errorMutable

    //Order on road
    private val orderMutable = MutableLiveData<ArrayList<IsOrderSuccessful>>()
    val orderLiveData : LiveData<ArrayList<IsOrderSuccessful>>
        get() = orderMutable

    private val retrofitAPI = AppModule.injectAPI()








   fun placeOrder(listModels : ArrayList<ListModel>){
       var counter = 0
       loadingMutable.value=true

       val isOrderSuccessfulList = ArrayList<IsOrderSuccessful>()
       listModels.forEach { listModel->

           val postModel = PostModel(listModel.id,listModel.itemCount)
           val tempList = ArrayList<PostModel>()
           tempList.add(postModel)
           retrofitAPI.placeOrder(tempList.toList()).enqueue(object : Callback<GetModel>{
               override fun onResponse(call: Call<GetModel>, response: Response<GetModel>) {

                   if(response.code()==200){
                       isOrderSuccessfulList.add(IsOrderSuccessful(listModel.id,listModel.name,true))
                       counter++
                       if(counter==listModels.size){
                           loadingMutable.value=false
                           orderMutable.value=isOrderSuccessfulList
                       }
                   }
                   else if(response.code()==404){
                       isOrderSuccessfulList.add(IsOrderSuccessful(listModel.id,listModel.name,false))
                       counter++
                       if(counter==listModels.size){
                           loadingMutable.value=false
                           orderMutable.value=isOrderSuccessfulList
                       }
                   }
                   else{
                       isOrderSuccessfulList.add(IsOrderSuccessful(listModel.id,listModel.name,false))
                       counter++
                       if(counter==listModels.size){
                           loadingMutable.value=false
                           orderMutable.value=isOrderSuccessfulList
                       }
                   }

               }

               override fun onFailure(call: Call<GetModel>, t: Throwable) {
                  loadingMutable.value=false
                   errorMutable.value=t.localizedMessage
               }

           })
       }



   }


}
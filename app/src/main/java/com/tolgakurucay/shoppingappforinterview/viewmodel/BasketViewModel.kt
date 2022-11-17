package com.tolgakurucay.shoppingappforinterview.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tolgakurucay.shoppingappforinterview.model.GetModel
import com.tolgakurucay.shoppingappforinterview.model.IsOrderSuccessful
import com.tolgakurucay.shoppingappforinterview.model.ListModel
import com.tolgakurucay.shoppingappforinterview.model.PostModel
import com.tolgakurucay.shoppingappforinterview.repository.CaseBasketRepository
import com.tolgakurucay.shoppingappforinterview.repository.CaseBasketRepositoryInterface
import com.tolgakurucay.shoppingappforinterview.utils.Resource
import com.tolgakurucay.shoppingappforinterview.utils.Status
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class BasketViewModel @Inject constructor(
    private val repository : CaseBasketRepositoryInterface
) : ViewModel() {

    private var isOrderSuccessfulMutable = MutableLiveData<Resource<ArrayList<IsOrderSuccessful>>>()
    val isOrderSuccessfulLiveData: LiveData<Resource<ArrayList<IsOrderSuccessful>>>
        get() = isOrderSuccessfulMutable


    fun placeOrderInViewModel(listModels : ArrayList<ListModel>){

        var counter = 0
        val isOrderSuccessfulList = arrayListOf<IsOrderSuccessful>()

        viewModelScope.launch {
        withContext(Dispatchers.IO){
            val TAG = "bbilgi"
            listModels.forEach {
                val postModel = PostModel(it.id,it.itemCount)

                val list = arrayListOf<PostModel>()
                list.add(postModel)


                val response = repository.placeOrder(list.toList())
                when(response.status){

                    Status.SUCCESS->{
                        counter++
                        isOrderSuccessfulList.add(IsOrderSuccessful(it.id,it.name,true))
                        if(listModels.size==counter){
                            // isOrderSuccessfulMutable.value= Resource.success(isOrderSuccessfulList)
                            isOrderSuccessfulMutable.postValue(Resource.success(isOrderSuccessfulList))
                        }
                    }
                    Status.ERROR->{
                        counter++
                        isOrderSuccessfulList.add(IsOrderSuccessful(it.id,it.name,false))
                        if(listModels.size==counter){
                            //isOrderSuccessfulMutable.value= Resource.success(isOrderSuccessfulList)
                            isOrderSuccessfulMutable.postValue(Resource.success(isOrderSuccessfulList))
                        }
                    }
                    Status.LOADING->{
                        isOrderSuccessfulMutable.postValue(Resource.loading(null))
                    }

                }





            }
        }







        }

    }


}
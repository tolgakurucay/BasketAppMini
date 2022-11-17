package com.tolgakurucay.shoppingappforinterview.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tolgakurucay.shoppingappforinterview.model.GetModel
import com.tolgakurucay.shoppingappforinterview.model.IsOrderSuccessful
import com.tolgakurucay.shoppingappforinterview.model.ListModel
import com.tolgakurucay.shoppingappforinterview.model.PostModel
import com.tolgakurucay.shoppingappforinterview.repository.CaseBasketRepository
import com.tolgakurucay.shoppingappforinterview.utils.Resource
import com.tolgakurucay.shoppingappforinterview.utils.Status
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BasketViewModel @Inject constructor(
    private val repository : CaseBasketRepository
) : ViewModel() {

    private var isOrderSuccessfulMutable = MutableLiveData<Resource<ArrayList<IsOrderSuccessful>>>()
    val isOrderSuccessfulLiveData: LiveData<Resource<ArrayList<IsOrderSuccessful>>>
        get() = isOrderSuccessfulMutable


    fun placeOrderInViewModel(listModels : ArrayList<ListModel>){

        viewModelScope.launch {

            var counter = 0
            val isOrderSuccessfulList = arrayListOf<IsOrderSuccessful>()


            listModels.forEach {
                val postModel = PostModel(it.id,it.itemCount)
                val response = repository.placeOrder(postModel)
                when(response.status){
                    Status.SUCCESS->{
                        isOrderSuccessfulList.add(IsOrderSuccessful(it.id,it.name,true))
                    }
                    Status.ERROR->{
                        isOrderSuccessfulList.add(IsOrderSuccessful(it.id,it.name,false))
                    }
                    else->{
                        isOrderSuccessfulList.add(IsOrderSuccessful(it.id,it.name,false))
                    }
                }
                counter++
                if(listModels.size==counter){
                    isOrderSuccessfulMutable.value= Resource.success(isOrderSuccessfulList)
                }

            }


        }

    }


}
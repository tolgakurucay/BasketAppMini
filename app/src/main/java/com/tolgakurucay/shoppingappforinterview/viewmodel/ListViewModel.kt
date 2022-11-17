package com.tolgakurucay.shoppingappforinterview.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tolgakurucay.shoppingappforinterview.model.ListModel
import com.tolgakurucay.shoppingappforinterview.repository.CaseListingRepository
import com.tolgakurucay.shoppingappforinterview.repository.CaseListingRepositoryInterface
import com.tolgakurucay.shoppingappforinterview.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(
    private val repository: CaseListingRepositoryInterface
) : ViewModel() {

    private var listModelMutableLiveData = MutableLiveData<Resource<List<ListModel>>>()
    val listModelLiveData: LiveData<Resource<List<ListModel>>>
        get() = listModelMutableLiveData



    fun resetListModel() {
        listModelMutableLiveData = MutableLiveData()
    }

    fun getItemsInViewModel() = viewModelScope.launch {
        withContext(Dispatchers.IO){
            listModelMutableLiveData.postValue(Resource.loading(null))
            val response = repository.getItems()
            listModelMutableLiveData.postValue(response)
        }






    }

}
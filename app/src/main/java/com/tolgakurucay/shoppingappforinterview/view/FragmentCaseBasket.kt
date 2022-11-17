package com.tolgakurucay.shoppingappforinterview.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.tolgakurucay.shoppingappforinterview.R
import com.tolgakurucay.shoppingappforinterview.adapter.BasketAdapter
import com.tolgakurucay.shoppingappforinterview.databinding.FragmentCaseBasketBinding
import com.tolgakurucay.shoppingappforinterview.service.TempSave
import com.tolgakurucay.shoppingappforinterview.utils.Extensions.showOneActionAlert
import com.tolgakurucay.shoppingappforinterview.utils.Status
import com.tolgakurucay.shoppingappforinterview.viewmodel.BasketViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class FragmentCaseBasket @Inject constructor(): Fragment() {

    private lateinit var viewBinding : FragmentCaseBasketBinding
    @Inject
    lateinit var basketAdapter: BasketAdapter

    private val viewModel : BasketViewModel by viewModels()


    private val TAG = "bilgi"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding = FragmentCaseBasketBinding.inflate(layoutInflater)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setup()
        buttonClickListeners()
        basketAdapter.countLiveData!!.observe(viewLifecycleOwner){
            it?.let {
                viewBinding.itemCount.text=it.toString()
            }
        }
        observeLiveData()


    }

    private fun setup(){
        viewBinding.basketRecyclerView.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)
        viewBinding.basketRecyclerView.adapter = basketAdapter
        basketAdapter.updateAdapter(TempSave.getListModel())
        Log.d(TAG, "basket ${TempSave.getListModel()}")
        

    }

    private fun buttonClickListeners(){
        viewBinding.placeOrderButton.setOnClickListener {
            val list = TempSave.getListModel()
            if(list.isNotEmpty()){
               // viewModel.placeOrder(list)
                viewModel.placeOrderInViewModel(list)
            }
            else{
                showOneActionAlert(getString(R.string.error),getString(R.string.add_items_to_basket)){
                    findNavController().navigateUp()
                }
            }


        }
        viewBinding.continueShoppingButton.setOnClickListener {
            findNavController().navigateUp()
        }
    }
    
    private fun observeLiveData(){

        viewModel.isOrderSuccessfulLiveData.observe(viewLifecycleOwner){
            it?.let {
                when(it.status){
                    Status.SUCCESS->{
                        hideLoading()
                        val newList = ArrayList<String>()
                        it.data?.let {
                            it.forEach { isOrderSuccessful ->
                                if(isOrderSuccessful.isOrderedSuccessful){
                                    newList.add(isOrderSuccessful.name+" ${getString(R.string.ordered_successfully)}")
                                }
                                else{
                                    newList.add(isOrderSuccessful.name+" ${getString(R.string.order_failed)}")
                                }

                            }
                        }
                        showOneActionAlert(getString(R.string.order_successfully),newList.toString()){
                            TempSave.removeAll()
                            findNavController().navigateUp()
                        }
                    }
                    Status.LOADING->{

                        showLoading()

                    }
                    Status.ERROR->{
                        hideLoading()
                        showOneActionAlert(getString(R.string.error),getString(R.string.order_has_failed)){

                        }
                    }
                }
            }
        }

      
    }
    
    private fun showLoading(){
        viewBinding.basketRecyclerView.visibility=View.INVISIBLE
        viewBinding.loadingCaseBasket.visibility=View.VISIBLE
    }
    
    private fun hideLoading(){
        viewBinding.basketRecyclerView.visibility=View.VISIBLE
        viewBinding.loadingCaseBasket.visibility=View.INVISIBLE
    }
    



}
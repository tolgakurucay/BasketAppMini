package com.tolgakurucay.shoppingappforinterview.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.tolgakurucay.shoppingappforinterview.adapter.BasketAdapter
import com.tolgakurucay.shoppingappforinterview.databinding.FragmentCaseBasketBinding
import com.tolgakurucay.shoppingappforinterview.service.TempSave
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class FragmentCaseBasket : Fragment() {

    private lateinit var viewBinding : FragmentCaseBasketBinding
    @Inject
    lateinit var basketAdapter: BasketAdapter

    private val TAG = "bilgi"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        viewBinding = FragmentCaseBasketBinding.inflate(layoutInflater)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setup()
        buttonClickListeners()
    }

    private fun setup(){
        viewBinding.basketRecyclerView.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)
        viewBinding.basketRecyclerView.adapter = basketAdapter
        basketAdapter.updateAdapter(TempSave.getListModel())
        Log.d(TAG, "basket ${TempSave.getListModel()}")

    }

    private fun buttonClickListeners(){
        viewBinding.placeOrderButton.setOnClickListener {

        }
        viewBinding.continueShoppingButton.setOnClickListener {
            findNavController().navigateUp()
        }
    }


}
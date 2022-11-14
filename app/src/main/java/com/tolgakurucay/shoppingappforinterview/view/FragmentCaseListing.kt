package com.tolgakurucay.shoppingappforinterview.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.tolgakurucay.shoppingappforinterview.adapter.ListAdapter
import com.tolgakurucay.shoppingappforinterview.model.ListModel
import com.tolgakurucay.shoppingappforinterview.databinding.FragmentCaseListingBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class FragmentCaseListing : Fragment() {
    private lateinit var viewBinding : FragmentCaseListingBinding
    @Inject
    lateinit var listAdapter : ListAdapter

    private val myList = ArrayList<ListModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        viewBinding = FragmentCaseListingBinding.inflate(layoutInflater)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setup()

    }

    private fun setup(){
        viewBinding.listingRecyclerView.layoutManager = GridLayoutManager(requireContext(),2)
        viewBinding.listingRecyclerView.adapter = listAdapter
        myList.add(ListModel(1,"Şampuan","13","TRY","https://cdn.glitch.com/a28552e7-44e1-4bbd-b298-5745e70c2209%2Fsampuan.jpeg?v=1561027551321"))
        listAdapter.updateAdapter(myList)
    }


}
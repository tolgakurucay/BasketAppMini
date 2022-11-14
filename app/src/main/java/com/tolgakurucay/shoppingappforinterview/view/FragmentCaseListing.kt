package com.tolgakurucay.shoppingappforinterview.view

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.tolgakurucay.shoppingappforinterview.R
import com.tolgakurucay.shoppingappforinterview.adapter.ListAdapter
import com.tolgakurucay.shoppingappforinterview.model.ListModel
import com.tolgakurucay.shoppingappforinterview.databinding.FragmentCaseListingBinding
import com.tolgakurucay.shoppingappforinterview.utils.Extensions.showOneActionAlert
import com.tolgakurucay.shoppingappforinterview.viewmodel.FragmentCaseListingViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@AndroidEntryPoint
class FragmentCaseListing : Fragment() {
    private lateinit var viewBinding : FragmentCaseListingBinding
    @Inject
    lateinit var listAdapter : ListAdapter

    private val viewModel : FragmentCaseListingViewModel by viewModels()

   // private val myList = ArrayList<ListModel>()
    private lateinit var globalMenu : Menu

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
        lifecycleScope.launch {
            viewModel.getItems()
        }
        observeLiveData()

    }

    private fun setup(){
        viewBinding.listingRecyclerView.layoutManager = GridLayoutManager(requireContext(),2)
        viewBinding.listingRecyclerView.adapter = listAdapter
        setHasOptionsMenu(true)


    }

    private fun observeLiveData(){
        viewModel.listModelLiveData.observe(viewLifecycleOwner){
            it?.let { listModel ->
                listAdapter.updateAdapter(listModel)
            }
        }

        viewModel.errorLiveData.observe(viewLifecycleOwner){
            it?.let {
                showOneActionAlert(getString(R.string.error),it){

                }
            }
        }

        viewModel.loadingLiveData.observe(viewLifecycleOwner){
            it?.let {
                if(it){
                    viewBinding.loadingCaseListing.visibility=View.VISIBLE
                }
                else{
                    viewBinding.loadingCaseListing.visibility=View.GONE
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        globalMenu=menu
        inflater.inflate(R.menu.menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
       return when (item.itemId) {
            R.id.basketMenuItem -> {
                val action = FragmentCaseListingDirections.actionFragmentCaseListingToFragmentCaseBasket()
                findNavController().navigate(action)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }

    }


}
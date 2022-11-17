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
import com.tolgakurucay.shoppingappforinterview.databinding.FragmentCaseListingBinding
import com.tolgakurucay.shoppingappforinterview.utils.Extensions.showShortToast
import com.tolgakurucay.shoppingappforinterview.utils.Status
import com.tolgakurucay.shoppingappforinterview.viewmodel.ListViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class FragmentCaseListing @Inject constructor(): Fragment() {
    private lateinit var viewBinding: FragmentCaseListingBinding

    @Inject
    lateinit var listAdapter: ListAdapter


    private val viewModel: ListViewModel by viewModels()

    private lateinit var globalMenu: Menu

    private val TAG = "bilgi"

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
            viewModel.getItemsInViewModel()
        }

        viewBinding.swipeLayout.setOnRefreshListener {
            showLoading()
            showShortToast(getString(R.string.refreshing))
            lifecycleScope.launch {
                viewModel.getItemsInViewModel()
            }
        }
        observeLiveData()

    }

    private fun setup() {
        viewBinding.listingRecyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
        viewBinding.listingRecyclerView.adapter = listAdapter
        setHasOptionsMenu(true)


    }

    private fun observeLiveData() {

        viewModel.listModelLiveData.observe(viewLifecycleOwner) {
            it?.let {
                when (it.status) {
                    Status.SUCCESS -> {
                       /* viewBinding.errorListing.visibility=View.INVISIBLE
                        viewBinding.loadingCaseListing.visibility=View.INVISIBLE*/
                        hideError()
                        viewBinding.swipeLayout.isRefreshing=false
                        hideLoading()
                        listAdapter.updateAdapter(it.data!!)
                    }
                    Status.LOADING -> {
                        /*viewBinding.errorListing.visibility=View.INVISIBLE
                        viewBinding.loadingCaseListing.visibility=View.VISIBLE*/
                        hideError()
                        showLoading()

                    }
                    Status.ERROR -> {
                        //viewBinding.errorListing.visibility=View.VISIBLE
                        showError()
                    }
                }
            }
        }

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        globalMenu = menu
        inflater.inflate(R.menu.menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.basketMenuItem -> {
                val action =
                    FragmentCaseListingDirections.actionFragmentCaseListingToFragmentCaseBasket()
                findNavController().navigate(action)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }

    }

    private fun showLoading() {
        viewBinding.loadingCaseListing.visibility = View.VISIBLE
        viewBinding.listingRecyclerView.visibility = View.INVISIBLE
        viewBinding.errorListing.visibility = View.INVISIBLE
    }

    private fun hideLoading() {
        viewBinding.loadingCaseListing.visibility = View.INVISIBLE
        viewBinding.listingRecyclerView.visibility = View.VISIBLE
        viewBinding.errorListing.visibility = View.INVISIBLE
    }

    private fun showError(){
        viewBinding.loadingCaseListing.visibility = View.INVISIBLE
        viewBinding.listingRecyclerView.visibility = View.INVISIBLE
        viewBinding.errorListing.visibility = View.VISIBLE
    }
    private fun hideError(){
        viewBinding.loadingCaseListing.visibility = View.INVISIBLE
        viewBinding.listingRecyclerView.visibility = View.INVISIBLE
        viewBinding.errorListing.visibility = View.INVISIBLE
    }


}
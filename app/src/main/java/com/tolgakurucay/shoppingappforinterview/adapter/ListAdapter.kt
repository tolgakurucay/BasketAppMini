package com.tolgakurucay.shoppingappforinterview.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tolgakurucay.shoppingappforinterview.databinding.ListItemRowBinding
import com.tolgakurucay.shoppingappforinterview.model.ListModel
import javax.inject.Inject


class ListAdapter @Inject constructor(): RecyclerView.Adapter<ListAdapter.ListViewHolder>() {
    var adapterList : ArrayList<ListModel> = ArrayList()

    class ListViewHolder(val binding : ListItemRowBinding)  : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view =ListItemRowBinding.inflate(inflater,parent,false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.binding.listObject = adapterList[position]
    }

    override fun getItemCount(): Int {
        return adapterList.size
    }

    fun updateAdapter(newAdapterList : ArrayList<ListModel>){
        adapterList = newAdapterList
        notifyDataSetChanged()
    }


}
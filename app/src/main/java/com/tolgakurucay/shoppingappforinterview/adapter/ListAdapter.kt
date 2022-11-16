package com.tolgakurucay.shoppingappforinterview.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.tolgakurucay.shoppingappforinterview.R
import com.tolgakurucay.shoppingappforinterview.databinding.ListItemRowBinding
import com.tolgakurucay.shoppingappforinterview.model.ListModel
import com.tolgakurucay.shoppingappforinterview.service.TempSave
import javax.inject.Inject


class ListAdapter @Inject constructor(): RecyclerView.Adapter<ListAdapter.ListViewHolder>() {
    private var adapterList : List<ListModel> = ArrayList()
    private val TAG = "bilgi"
    class ListViewHolder(val binding : ListItemRowBinding)  : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view =ListItemRowBinding.inflate(inflater,parent,false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.binding.listObject = adapterList[position]
        holder.binding.addBasketButton.setOnClickListener {
            val listObject = adapterList[position]
            addToBasket(listObject,holder,position)

        }
    }

    private fun addToBasket(listObject: ListModel,holder : ListViewHolder,position:Int) {
        Toast.makeText(holder.binding.root.context, "${adapterList[position].name} ${holder.binding.root.context.getString(R.string.has_added)}", Toast.LENGTH_SHORT).show()
        val list = TempSave.getListModel()
        if(list.isEmpty()){
            listObject.itemCount++
            TempSave.addListModel(listObject)
            Log.d(TAG, "ahahaha ${adapterList[position]}")
        }
        else{
            var ifExist=false
            list.forEach {
                if(listObject.id==it.id && !ifExist){
                    it.itemCount++
                    ifExist=true
                }
            }

            if(!ifExist){
                listObject.itemCount++
                TempSave.addListModel(listObject)
            }

        }
    }

    override fun getItemCount(): Int {
        return adapterList.size
    }

    fun updateAdapter(newAdapterList : List<ListModel>){
        adapterList = newAdapterList
        notifyDataSetChanged()
    }




}
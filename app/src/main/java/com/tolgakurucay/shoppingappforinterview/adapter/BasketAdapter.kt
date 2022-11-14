package com.tolgakurucay.shoppingappforinterview.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tolgakurucay.shoppingappforinterview.databinding.BasketItemLayoutBinding
import com.tolgakurucay.shoppingappforinterview.model.ListModel
import javax.inject.Inject

class BasketAdapter @Inject constructor(): RecyclerView.Adapter<BasketAdapter.BasketHolder>() {

    private var basketList : ArrayList<ListModel> = ArrayList()

    class BasketHolder(val binding : BasketItemLayoutBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BasketHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = BasketItemLayoutBinding.inflate(inflater,parent,false)
        return BasketHolder(view)
    }

    override fun onBindViewHolder(holder: BasketHolder, position: Int) {
       holder.binding.listObject = basketList[position]
        clickListeners(holder,position)
    }

    override fun getItemCount(): Int {
       return basketList.size
    }

    fun updateAdapter(newModelList : ArrayList<ListModel>){
        basketList = newModelList
        notifyDataSetChanged()
    }

    private fun clickListeners(holder: BasketHolder, position: Int){
        holder.binding.imageViewRemove.setOnClickListener {

        }
        holder.binding.imageViewAdd.setOnClickListener {

        }
        holder.binding.textViewRemove.setOnClickListener{

        }
    }
}
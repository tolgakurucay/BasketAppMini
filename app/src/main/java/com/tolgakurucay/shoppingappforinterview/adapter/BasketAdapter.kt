package com.tolgakurucay.shoppingappforinterview.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.tolgakurucay.shoppingappforinterview.databinding.BasketItemLayoutBinding
import com.tolgakurucay.shoppingappforinterview.model.ListModel
import com.tolgakurucay.shoppingappforinterview.service.TempSave
import javax.inject.Inject

class BasketAdapter @Inject constructor(): RecyclerView.Adapter<BasketAdapter.BasketHolder>() {

    private var basketList : ArrayList<ListModel> = ArrayList()

    private val mutableCount = MutableLiveData<Int>()
    val countLiveData : LiveData<Int>?
    get() = mutableCount



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
        mutableCount.value=basketList.size
    }

    private fun clickListeners(holder: BasketHolder, position: Int){
        holder.binding.imageViewRemove.setOnClickListener {

            if(basketList[position].itemCount>0){
                basketList[position].itemCount--
                if(basketList[position].itemCount==0) TempSave.removeListModel(basketList[position])
                notifyDataSetChanged()
                mutableCount.value=basketList.size
            }
            else{
                TempSave.removeListModel(basketList[position])
                notifyDataSetChanged()
                mutableCount.value=basketList.size
            }
        }
        holder.binding.imageViewAdd.setOnClickListener {
            basketList[position].itemCount++
            notifyDataSetChanged()
            mutableCount.value=basketList.size
        }
        holder.binding.textViewRemove.setOnClickListener{
            TempSave.removeListModel(basketList[position])
            notifyDataSetChanged()
            mutableCount.value=basketList.size
        }
    }

}
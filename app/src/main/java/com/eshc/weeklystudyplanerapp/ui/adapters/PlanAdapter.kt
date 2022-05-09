package com.eshc.weeklystudyplanerapp.ui.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatCheckBox
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.eshc.weeklystudyplanerapp.R
import com.eshc.weeklystudyplanerapp.data.entity.Plan
import com.eshc.weeklystudyplanerapp.databinding.ItemPlanBinding
import com.eshc.weeklystudyplanerapp.viewmodel.MainViewModel

class PlanAdapter(val vm:MainViewModel) :
    RecyclerView.Adapter<PlanAdapter.PlanViewHolder>() {
    val items = mutableListOf<Plan>()


    inner class PlanViewHolder(val binding: ItemPlanBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(plan: Plan){
            binding.plan = plan

//            binding.ivDelete.setOnClickListener {
//                vm.deletePlan(plan)
//            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlanViewHolder {
        val binding = ItemPlanBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return PlanViewHolder(binding)
    }


    override fun onBindViewHolder(holder: PlanViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }
    fun replaceAll(items: List<Plan>?) {
        items?.let {
            this.items.run {
                clear()
                addAll(it)
                notifyDataSetChanged()
            }
        }
    }
    fun deleteItem(index : Int){
        if(index < items.size){
            items.removeAt(index)
            notifyItemRemoved(index)
        }
    }
}
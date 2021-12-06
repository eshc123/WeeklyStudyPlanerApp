package com.eshc.weeklystudyplanerapp.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.eshc.weeklystudyplanerapp.R
import com.eshc.weeklystudyplanerapp.data.entity.Plan

class PlanAdapter(private val plans: List<Plan>) :
    RecyclerView.Adapter<PlanAdapter.PlanViewHolder>() {

    inner class PlanViewHolder(view : View) : RecyclerView.ViewHolder(view){
        val subjectTextView : AppCompatTextView = view.findViewById(R.id.tv_subject)
        val titleTextView : AppCompatTextView = view.findViewById(R.id.tv_title)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlanViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_plan,parent,false)
        return PlanViewHolder(view)
    }

    override fun onBindViewHolder(holder: PlanViewHolder, position: Int) {
        holder.titleTextView.text = plans.get(position).title
        holder.subjectTextView.text = plans.get(position).subjectId.toString()
    }

    override fun getItemCount(): Int {
        return plans.size
    }
}
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

class PlanAdapter :
    RecyclerView.Adapter<PlanAdapter.PlanViewHolder>() {
    val items = mutableListOf<Plan>()


    inner class PlanViewHolder(view : View) : RecyclerView.ViewHolder(view){
        val subjectTextView : AppCompatTextView = view.findViewById(R.id.tv_subject)
        val titleTextView : AppCompatTextView = view.findViewById(R.id.tv_title)
        val timeTextView : AppCompatTextView = view.findViewById(R.id.tv_time)
        val doneCheckBox : AppCompatCheckBox = view.findViewById(R.id.cb_done)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlanViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_plan,parent,false)
        return PlanViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: PlanViewHolder, position: Int) {
        holder.titleTextView.text = items.get(position).title
        holder.subjectTextView.text = items.get(position).subjectId.toString()
        holder.timeTextView.text = "${items.get(position).startTime} ~ ${items.get(position).finishTime}"
        holder.doneCheckBox.isChecked = items.get(position).done
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
}
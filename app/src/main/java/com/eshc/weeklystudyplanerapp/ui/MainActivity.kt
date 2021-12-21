package com.eshc.weeklystudyplanerapp.ui

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.widget.AppCompatTextView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.eshc.weeklystudyplanerapp.MainApplication
import com.eshc.weeklystudyplanerapp.R
import com.eshc.weeklystudyplanerapp.data.entity.Plan
import com.eshc.weeklystudyplanerapp.databinding.ActivityMainBinding
import com.eshc.weeklystudyplanerapp.ui.adapters.PlanAdapter
import com.eshc.weeklystudyplanerapp.viewmodel.MainModelFactory
import com.eshc.weeklystudyplanerapp.viewmodel.MainViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    private val newPlanActivityRequestCode = 1
    private val mainVM : MainViewModel by viewModels {
        MainModelFactory((application as MainApplication).planRepository)
    }
    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        val adapter = PlanAdapter()
        binding.rvPlan.adapter = adapter
        binding.rvPlan.layoutManager = LinearLayoutManager(this)
        binding.tvToday.text = getToday()
        mainVM.allPlans.observe(this){ plans ->
            adapter.replaceAll(plans)
        }

        binding.fabAdd.setOnClickListener {
            val intent = Intent(this@MainActivity,NewPlanActivity::class.java)
            startActivityForResult(intent,newPlanActivityRequestCode)
        }
        binding.tvDeleteAll.setOnClickListener {
            mainVM.deleteAll()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == newPlanActivityRequestCode && resultCode == Activity.RESULT_OK) {
            val title = data?.getStringExtra(NewPlanActivity.EXTRA_TITLE) ?: ""
            val start = data?.getStringExtra(NewPlanActivity.EXTRA_START) ?: "0000"
            val finish = data?.getStringExtra(NewPlanActivity.EXTRA_FINISH) ?: "2400"
            val plan = Plan(day = 1,title = title,startTime = start,finishTime = finish,subjectId = 1,done = false)
            mainVM.insert(plan)
        }
    }
    private fun getToday():String {
        val data = Calendar.getInstance().time
        return SimpleDateFormat("yyyy년 MM월 dd일 EE요일",Locale.getDefault()).format(data)
    }
}
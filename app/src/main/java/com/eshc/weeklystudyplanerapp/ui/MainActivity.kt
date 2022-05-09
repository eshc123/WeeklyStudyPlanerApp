package com.eshc.weeklystudyplanerapp.ui

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatSpinner
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.view.isVisible
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
import com.google.android.material.tabs.TabLayout
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    private val newPlanActivityRequestCode = 1
    private val mainVM : MainViewModel by viewModels {
        MainModelFactory((application as MainApplication).planRepository)
    }
    private lateinit var binding : ActivityMainBinding
    private lateinit var todayAdapter : PlanAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        val adapter = PlanAdapter(mainVM)
        todayAdapter = PlanAdapter(mainVM)
        binding.rvPlan.adapter = adapter
        binding.rvPlan.layoutManager = LinearLayoutManager(this)
        binding.rvPlanToday.adapter = todayAdapter
        binding.rvPlanToday.layoutManager = LinearLayoutManager(this)
        binding.tvWeek.text = getTodayTitle()

        mainVM.allPlans.observe(this){ plans ->
            adapter.replaceAll(plans)
        }

        binding.fabAdd.setOnClickListener {
            getDialog()
//            val intent = Intent(this@MainActivity,NewPlanActivity::class.java)
//            startActivityForResult(intent,newPlanActivityRequestCode)
        }

        //getDayPlans(Calendar.getInstance().get(Calendar.DAY_OF_WEEK))
        binding.tvWeek.setOnClickListener {
            binding.rvPlan.visibility = View.VISIBLE
            binding.rvPlanToday.visibility = View.GONE
        }
        initTab()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == newPlanActivityRequestCode && resultCode == Activity.RESULT_OK) {
            val title = data?.getStringExtra(NewPlanActivity.EXTRA_TITLE) ?: ""
            val start = data?.getStringExtra(NewPlanActivity.EXTRA_START) ?: "0000"
            val finish = data?.getStringExtra(NewPlanActivity.EXTRA_FINISH) ?: "2400"
            val day = data?.getIntExtra(NewPlanActivity.EXTRA_DAY,0) ?: 0
            val plan = Plan(day = day,title = title,startTime = start,finishTime = finish,subjectId = 1,done = false)
            Log.d("NewPlan","day : ${day} , title : ${title}, time : ${start} ~ ${finish}")
            mainVM.insert(plan)
        }
    }

    private fun getTodayTitle():String {
        val time = Calendar.getInstance().time

        val calendar = Calendar.getInstance()
        calendar.time = time
        calendar.firstDayOfWeek = Calendar.MONDAY
        calendar.add(Calendar.DAY_OF_MONTH,2-calendar.get(Calendar.DAY_OF_WEEK))
        val monday = SimpleDateFormat("yyyy.MM.dd ~ ",Locale.getDefault()).format(calendar.time)
        calendar.add(Calendar.DAY_OF_MONTH,8-calendar.get(Calendar.DAY_OF_WEEK))
        val sunday = SimpleDateFormat("MM.dd",Locale.getDefault()).format(calendar.time)
        return monday + sunday
    }

    private fun initTab() {
        binding.tlDay.apply {
            this.addTab(this.newTab().setText("월"))
            this.addTab(this.newTab().setText("화"))
            this.addTab(this.newTab().setText("수"))
            this.addTab(this.newTab().setText("목"))
            this.addTab(this.newTab().setText("금"))
            this.addTab(this.newTab().setText("토"))
            this.addTab(this.newTab().setText("일"))
        }
        binding.tlDay.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab) {

            }

            override fun onTabUnselected(tab: TabLayout.Tab) {

            }

            override fun onTabSelected(tab: TabLayout.Tab) {
                getDayPlans((tab.position+2)%7)
            }
        })
        binding.tlDay.selectTab(binding.tlDay.getTabAt((Calendar.getInstance().get(Calendar.DAY_OF_WEEK) -2)%7 ))
    }
    fun getDayPlans(day : Int){
        mainVM.todayPlans(day-1).observe(this) { plans ->
            todayAdapter.replaceAll(plans)
        }
    }
    fun getDialog(){
        AlertDialog.Builder(this,R.style.AlertDialogTheme)
            .setView(R.layout.activity_new_plan)
            .show()
            .also { alertDialog ->

                if(alertDialog == null) {
                    return@also
                }
                val planButton = alertDialog.findViewById<AppCompatButton>(R.id.bt_ok)
                val spinner = alertDialog.findViewById<AppCompatSpinner>(R.id.sp_days)
                val etTitle = alertDialog.findViewById<AppCompatEditText>(R.id.et_title)
                var day = 0
                planButton?.setOnClickListener {

                    val title = etTitle?.text.toString()
                    val plan = Plan(day = day,title = title,startTime = "0000",finishTime = "0111",subjectId = 1,done = false)

                    mainVM.insert(plan)

                    alertDialog.dismiss()
                }


                ArrayAdapter.createFromResource(
                    this,
                    R.array.days_array,
                    android.R.layout.simple_spinner_item
                ).also { adapter ->
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                    spinner?.adapter = adapter
                }
                spinner?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
                    override fun onItemSelected(
                        parent: AdapterView<*>?,
                        view: View?,
                        position: Int,
                        id: Long
                    ) {
                        day = position
                    }

                    override fun onNothingSelected(parent: AdapterView<*>?) {

                    }

                }
            }
    }

}
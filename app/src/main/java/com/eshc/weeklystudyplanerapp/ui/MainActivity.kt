package com.eshc.weeklystudyplanerapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.eshc.weeklystudyplanerapp.MainApplication
import com.eshc.weeklystudyplanerapp.R
import com.eshc.weeklystudyplanerapp.ui.adapters.PlanAdapter
import com.eshc.weeklystudyplanerapp.viewmodel.MainModelFactory
import com.eshc.weeklystudyplanerapp.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {
    private val mainVM : MainViewModel by viewModels {
        MainModelFactory((application as MainApplication).planRepository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.rv_plan)
        val adapter = PlanAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        mainVM.allPlans.observe(this){ plans ->
            adapter.replaceAll(plans)
        }
    }
}
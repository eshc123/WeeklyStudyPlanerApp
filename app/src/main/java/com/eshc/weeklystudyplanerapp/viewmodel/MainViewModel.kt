package com.eshc.weeklystudyplanerapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.eshc.weeklystudyplanerapp.data.entity.Plan
import com.eshc.weeklystudyplanerapp.data.repository.PlanRepository
import kotlinx.coroutines.launch

class MainViewModel(private val repository: PlanRepository) : ViewModel() {

    val allPlans : LiveData<List<Plan>> = repository.allPlans.asLiveData()

    fun insert(plan: Plan) = viewModelScope.launch {
        repository.insert(plan)
    }
}
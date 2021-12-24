package com.eshc.weeklystudyplanerapp.viewmodel

import androidx.lifecycle.*
import com.eshc.weeklystudyplanerapp.data.entity.Plan
import com.eshc.weeklystudyplanerapp.data.repository.PlanRepository
import kotlinx.coroutines.launch

class MainViewModel(private val repository: PlanRepository) : ViewModel() {

    val allPlans : LiveData<List<Plan>> = repository.allPlans.asLiveData()
    fun todayPlans(today: Int) : LiveData<List<Plan>> = repository.todayPlans(today).asLiveData()

    fun insert(plan: Plan) = viewModelScope.launch {
        repository.insert(plan)
    }
    fun deleteAll() = viewModelScope.launch {
        repository.deleteALl()
    }
    fun deletePlan(vararg plans: Plan) = viewModelScope.launch {
        repository.deletePlan(plans = plans)
    }
}
class MainModelFactory(private val repository: PlanRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MainViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
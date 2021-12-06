package com.eshc.weeklystudyplanerapp.viewmodel

import androidx.lifecycle.*
import com.eshc.weeklystudyplanerapp.data.entity.Plan
import com.eshc.weeklystudyplanerapp.data.repository.PlanRepository
import kotlinx.coroutines.launch

class MainViewModel(private val repository: PlanRepository) : ViewModel() {

    val allPlans : LiveData<List<Plan>> = repository.allPlans.asLiveData()

    fun insert(plan: Plan) = viewModelScope.launch {
        repository.insert(plan)
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
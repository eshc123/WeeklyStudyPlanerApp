package com.eshc.weeklystudyplanerapp.data.repository

import androidx.annotation.WorkerThread
import com.eshc.weeklystudyplanerapp.data.dao.PlanDao
import com.eshc.weeklystudyplanerapp.data.entity.Plan
import kotlinx.coroutines.flow.Flow

class PlanRepository(private val planDao : PlanDao) {

    val allPlans : Flow<List<Plan>> = planDao.getPlans()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(plan: Plan){
        planDao.insertPlan(plan)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun deleteALl(){
        planDao.deleteAll()
    }
}
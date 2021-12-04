package com.eshc.weeklystudyplanerapp.data.dao

import androidx.room.*
import com.eshc.weeklystudyplanerapp.data.entity.Plan
import kotlinx.coroutines.flow.Flow

@Dao
interface PlanDao {
    @Query("SELECT * FROM plan_table ORDER BY day, start_time")
    fun getPlans() : Flow<List<Plan>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertPlan(plan : Plan)

    @Delete
    fun deletePlan(plan : Plan)

}
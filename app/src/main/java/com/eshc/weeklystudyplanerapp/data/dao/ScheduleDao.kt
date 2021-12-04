package com.eshc.weeklystudyplanerapp.data.dao

import androidx.room.*
import com.eshc.weeklystudyplanerapp.data.entity.Schedule
import com.eshc.weeklystudyplanerapp.data.entity.Subject
import kotlinx.coroutines.flow.Flow

@Dao
interface ScheduleDao {
    @Query("SELECT * FROM schedule_table")
    fun getSchedules() : Flow<List<Schedule>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertSchedule(schedule : Schedule)

    @Delete
    fun deleteSchedule(schedule : Schedule)
    
}
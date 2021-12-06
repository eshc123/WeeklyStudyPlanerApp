package com.eshc.weeklystudyplanerapp.data.repository

import androidx.annotation.WorkerThread
import com.eshc.weeklystudyplanerapp.data.dao.ScheduleDao
import com.eshc.weeklystudyplanerapp.data.entity.Schedule
import kotlinx.coroutines.flow.Flow

class ScheduleRepository(private val scheduleDao: ScheduleDao) {

    val allSchedules : Flow<List<Schedule>> = scheduleDao.getSchedules()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(schedule: Schedule){
        scheduleDao.insertSchedule(schedule)
    }
}
package com.eshc.weeklystudyplanerapp.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.eshc.weeklystudyplanerapp.data.dao.PlanDao
import com.eshc.weeklystudyplanerapp.data.dao.ScheduleDao
import com.eshc.weeklystudyplanerapp.data.dao.SubjectDao

import com.eshc.weeklystudyplanerapp.data.entity.Plan
import com.eshc.weeklystudyplanerapp.data.entity.Schedule
import com.eshc.weeklystudyplanerapp.data.entity.Subject

@Database(entities = [Plan::class, Subject::class, Schedule::class],version = 1,exportSchema = false)
public abstract class AppDatabase : RoomDatabase() {

    abstract fun planDao() : PlanDao
    abstract fun subjectDao() : SubjectDao
    abstract fun scheduleDao() : ScheduleDao

    companion object {
        @Volatile
        private var INSTANCE : AppDatabase? = null

        fun getDatabase(context : Context) : AppDatabase{
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
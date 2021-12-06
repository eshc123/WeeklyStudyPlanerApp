package com.eshc.weeklystudyplanerapp.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.eshc.weeklystudyplanerapp.data.dao.PlanDao
import com.eshc.weeklystudyplanerapp.data.dao.SubjectDao

import com.eshc.weeklystudyplanerapp.data.entity.Plan
import com.eshc.weeklystudyplanerapp.data.entity.Subject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = [Plan::class, Subject::class] ,version = 1,exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun planDao() : PlanDao
    abstract fun subjectDao() : SubjectDao

    private class AppDatabaseCallBack(
        private val scope : CoroutineScope
    ) : RoomDatabase.Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                scope.launch {
                    val planDao = database.planDao()
                    planDao.deleteAll()

                    var plan = Plan(day = 1,title = "DataStructure",startTime = "0610",finishTime = "0800",subjectId = 1,done = false)
                    planDao.insertPlan(plan)
                    plan = Plan(day = 1,title = "ComputerNetwork",startTime = "0910",finishTime = "1200",subjectId = 2,done = true)
                    planDao.insertPlan(plan)
                    plan = Plan(day = 1,title = "OperatingSystem",startTime = "0810",finishTime = "0900",subjectId = 3,done = false)
                    planDao.insertPlan(plan)

                }
            }
        }
    }

    companion object {
        @Volatile
        private var INSTANCE : AppDatabase? = null

        fun getDatabase(context: Context, applicationScope: CoroutineScope) : AppDatabase{
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                )
                    .addCallback(AppDatabaseCallBack(scope = applicationScope))
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
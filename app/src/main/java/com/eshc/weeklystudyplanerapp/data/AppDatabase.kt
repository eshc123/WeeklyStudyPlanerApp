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

                    var plan = Plan(0,1,"DataStructure","0610","0800",1,false)
                    planDao.insertPlan(plan)
                    plan = Plan(1,1,"ComputerNetwork","0910","1200",2,true)
                    planDao.insertPlan(plan)
                    plan = Plan(2,1,"OperatingSystem","0810","0900",3,false)
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
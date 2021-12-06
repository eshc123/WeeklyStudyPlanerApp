package com.eshc.weeklystudyplanerapp

import android.app.Application
import com.eshc.weeklystudyplanerapp.data.AppDatabase
import com.eshc.weeklystudyplanerapp.data.repository.PlanRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class MainApplication : Application() {
    val applicationScope = CoroutineScope(SupervisorJob())

    val database by lazy { AppDatabase.getDatabase(this,applicationScope) }
    val planRepository by lazy { PlanRepository(database.planDao()) }
}
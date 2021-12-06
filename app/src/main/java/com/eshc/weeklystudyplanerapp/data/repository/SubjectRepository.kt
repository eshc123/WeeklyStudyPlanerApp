package com.eshc.weeklystudyplanerapp.data.repository

import androidx.annotation.WorkerThread
import com.eshc.weeklystudyplanerapp.data.dao.PlanDao
import com.eshc.weeklystudyplanerapp.data.dao.SubjectDao
import com.eshc.weeklystudyplanerapp.data.entity.Plan
import com.eshc.weeklystudyplanerapp.data.entity.Subject
import kotlinx.coroutines.flow.Flow

class SubjectRepository(private val subjectDao: SubjectDao) {

    val allSubjects : Flow<List<Subject>> = subjectDao.getSubjects()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(subject: Subject){
        subjectDao.insertSubject(subject)
    }
}
package com.eshc.weeklystudyplanerapp.data.dao

import androidx.room.*
import com.eshc.weeklystudyplanerapp.data.entity.Subject
import kotlinx.coroutines.flow.Flow

@Dao
interface SubjectDao {
    @Query("SELECT * FROM subject_table")
    fun getSubjects() : Flow<List<Subject>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertSubject(subject : Subject)

    @Delete
    fun deleteSubject(subject : Subject)
    
}
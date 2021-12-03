package com.eshc.weeklystudyplanerapp.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "plan_table")
data class Plan(
    @PrimaryKey val id : Int,
    val day : String,
    val title : String,
    @ColumnInfo(name = "start_time") val startTime : String,
    @ColumnInfo(name = "finish_time") val finishTime : String,
    @ColumnInfo(name = "subject_id") val subjectId : Int // 분야
)

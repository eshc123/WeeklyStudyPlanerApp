package com.eshc.weeklystudyplanerapp.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "schedule_table")
data class Schedule(
    @PrimaryKey val id : Int,
    @ColumnInfo(name = "plan_id") val pId : Int,
    @ColumnInfo(name = "subject_id") val sId : Int,
    val done : Boolean
)

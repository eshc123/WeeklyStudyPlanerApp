package com.eshc.weeklystudyplanerapp.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "subject_table")
data class Subject(
    @PrimaryKey val id : Int,
    val area : String
)

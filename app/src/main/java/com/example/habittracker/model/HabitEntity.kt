package com.example.habittracker.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "habits")
data class HabitEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,
    val duration: Int,
    val status: HabitStatus = HabitStatus.INCOMPLETE
)
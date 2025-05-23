package com.example.habittracker.model

import androidx.room.TypeConverter

class HabitStatusConverter {
    @TypeConverter
    fun fromStatus(status: HabitStatus): String = status.name

    @TypeConverter
    fun toStatus(status: String): HabitStatus = HabitStatus.valueOf(status)
}
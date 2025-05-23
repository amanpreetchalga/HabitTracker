package com.example.habittracker.data

import com.example.habittracker.model.HabitEntity
import kotlinx.coroutines.flow.Flow

open class HabitRepository(private val dao: HabitDao) {

    val habits: Flow<List<HabitEntity>> = dao.getAllHabits()

    suspend fun addHabit(habit: HabitEntity) = dao.insertHabit(habit)

    suspend fun updateHabit(habit: HabitEntity) = dao.updateHabit(habit)

    suspend fun deleteHabit(habit: HabitEntity) = dao.deleteHabit(habit)
}
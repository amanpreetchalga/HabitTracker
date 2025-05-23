package com.example.habittracker.ui.viewmodels

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.habittracker.data.HabitRepository
import com.example.habittracker.model.Habit
import com.example.habittracker.model.HabitEntity
import com.example.habittracker.model.HabitStatus
import kotlinx.coroutines.launch

open class HabitViewModel(
    private val repository: HabitRepository
) : ViewModel() {

    // Reactive list — Compose will observe changes
    private val _habits = mutableStateListOf<Habit>()
    val habits: List<Habit> get() = _habits

    init {
        viewModelScope.launch {
            repository.habits.collect { entities ->
                _habits.clear()
                _habits.addAll(entities.map {
                    Habit(it.id, it.title, it.duration, it.status)
                })
            }
        }
    }

    fun addHabit(title: String, duration: Int) {
        viewModelScope.launch {
            val entity = HabitEntity(title = title, duration = duration, status = HabitStatus.INCOMPLETE)
            repository.addHabit(entity)
        }
    }

    fun markComplete(habitId: Int) {
        viewModelScope.launch {
            val habit = _habits.find { it.id == habitId } ?: return@launch
            repository.updateHabit(
                HabitEntity(habit.id, habit.title, habit.duration, HabitStatus.COMPLETE)
            )
        }
    }

    fun deleteHabit(habitId: Int) {
        viewModelScope.launch {
            val habit = _habits.find { it.id == habitId } ?: return@launch
            repository.deleteHabit(
                HabitEntity(habit.id, habit.title, habit.duration, habit.status)
            )
        }
    }

    fun updateHabit(id: Int, title: String, duration: Int) {
        viewModelScope.launch {
            repository.updateHabit(
                HabitEntity(id = id, title = title, duration = duration, status = HabitStatus.INCOMPLETE)
            )
        }
    }
}
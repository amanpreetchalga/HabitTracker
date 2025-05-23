package com.example.habittracker.ui.viewmodels

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.habittracker.model.Habit
import com.example.habittracker.model.HabitStatus

class HabitViewModel : ViewModel() {

    // Reactive list — Compose will observe changes
    private val _habits = mutableStateListOf<Habit>()
    val habits: List<Habit> get() = _habits

    init {
        _habits.addAll(
            listOf(
                Habit(0, "", 0, HabitStatus.EMPTY),
                Habit(1, "Workout", 30, HabitStatus.INCOMPLETE),
                Habit(2, "Read Book", 20, HabitStatus.COMPLETE),
            )
        )
    }

    fun addHabit(title: String, duration: Int) {
        val newId = (_habits.maxOfOrNull { it.id } ?: 0) + 1
        _habits.add(Habit(newId, title, duration, HabitStatus.INCOMPLETE))
    }

    fun markComplete(habitId: Int) {
        val index = _habits.indexOfFirst { it.id == habitId }
        if (index != -1) {
            _habits[index] = _habits[index].copy(status = HabitStatus.COMPLETE)
        }
    }
}
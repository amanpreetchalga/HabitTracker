package com.example.habittracker.ui.screens

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import com.example.habittracker.model.Habit
import com.example.habittracker.model.HabitStatus
import com.example.habittracker.ui.components.HabitCard

/**
 * Displays a list of [HabitCard] composables using a LazyColumn.
 *
 * The list is currently static and showcases different habit states
 * (empty, incomplete, complete) for UI testing and visualization.
 */
@Composable
fun HabitList() {
    val habits = listOf(
        Habit(0, status = HabitStatus.EMPTY),
        Habit(1, "Workout", 45, HabitStatus.INCOMPLETE),
        Habit(2, "Meditate", 10, HabitStatus.COMPLETE),
        Habit(3, "Study Android", 30, HabitStatus.INCOMPLETE),
        Habit(4, "Read Book", 25, HabitStatus.COMPLETE)
    )

    LazyColumn {
        items(habits) { habit ->
            HabitCard(habit = habit)
        }
    }
}

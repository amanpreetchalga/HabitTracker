package com.example.habittracker.model

/**
 * Represents the current status of a habit.
 */
enum class HabitStatus {
    EMPTY, INCOMPLETE, COMPLETE
}

/**
 * Data class that defines the structure of a habit.
 *
 * @param id Unique identifier of the habit.
 * @param title The title or name of the habit.
 * @param duration Duration in minutes associated with the habit.
 * @param status The [HabitStatus] indicating the habit's current state.
 */
data class Habit(
    val id: Int,
    val title: String = "",
    val duration: Int = 0,
    val status: HabitStatus
)
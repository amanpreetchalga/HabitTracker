package com.example.habittracker.model

import org.junit.Assert.assertEquals
import org.junit.Test

class HabitTest {

    @Test
    fun `habit duration should be correct`() {
        val habit = Habit(2, "Read", 20, HabitStatus.INCOMPLETE)
        assertEquals(20, habit.duration)
    }

    @Test
    fun `habit copy with updated status should reflect correctly`() {
        val habit = Habit(1, "Meditate", 15, HabitStatus.INCOMPLETE)
        assertEquals(HabitStatus.INCOMPLETE, habit.status)
        val updatedHabit = habit.copy(status = HabitStatus.COMPLETE)
        assertEquals(HabitStatus.COMPLETE, updatedHabit.status)
    }
}
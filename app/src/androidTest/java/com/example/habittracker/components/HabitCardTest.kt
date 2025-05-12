package com.example.habittracker.components

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.habittracker.model.Habit
import com.example.habittracker.model.HabitStatus
import com.example.habittracker.ui.components.HabitCard
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class HabitCardTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun habitCard_displaysTitleAndDuration_whenIncomplete() {
        val habit = Habit(1, "Workout", 30, HabitStatus.INCOMPLETE)

        composeTestRule.setContent {
            HabitCard(habit)
        }

        composeTestRule.onNodeWithText("Workout").assertExists()
        composeTestRule.onNodeWithText("Duration: 30 mins").assertExists()
        composeTestRule.onNodeWithContentDescription("Start").assertExists()
        composeTestRule.onNodeWithContentDescription("Edit").assertExists()
        composeTestRule.onNodeWithContentDescription("Complete").assertExists()
        composeTestRule.onNodeWithContentDescription("Delete").assertExists()
    }

    @Test
    fun emptyHabitCard_displaysAddIcon() {
        val habit = Habit(0, "", 0, HabitStatus.EMPTY)

        composeTestRule.setContent {
            HabitCard(habit)
        }

        composeTestRule.onNodeWithContentDescription("Add Habit").assertExists()
    }
}

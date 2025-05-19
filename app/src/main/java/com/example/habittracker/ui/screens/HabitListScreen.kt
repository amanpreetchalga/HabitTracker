package com.example.habittracker.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.habittracker.model.Habit
import com.example.habittracker.model.HabitStatus
import com.example.habittracker.ui.components.HabitCard
import com.example.habittracker.ui.navigation.Routes

@Composable
fun HabitListScreen(navController: NavController) {
    val habits = listOf(
        Habit(0, "", 0, HabitStatus.EMPTY),
        Habit(1, "Workout", 30, HabitStatus.INCOMPLETE),
        Habit(2, "Read Book", 20, HabitStatus.COMPLETE),
    )

    Column(modifier = Modifier.fillMaxSize()) {
        LazyColumn(modifier = Modifier.weight(1f)) {
            items(habits.size) { index ->
                HabitCard(habit = habits[index])
            }
        }

        Button(
            onClick = { navController.navigate(Routes.HABIT_FORM) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text("Add Habit")
        }
    }
}
package com.example.habittracker.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.habittracker.ui.components.HabitCard
import com.example.habittracker.ui.viewmodels.HabitViewModel
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun HabitListScreen(
    navController: NavController,
    viewModel: HabitViewModel = viewModel()
) {
    val habits = viewModel.habits

    Column(modifier = Modifier.fillMaxSize()) {
        LazyColumn(modifier = Modifier.weight(1f)) {
            items(habits) { habit ->
                HabitCard(habit = habit) // can later pass viewModel.markComplete()
            }
        }

        Button(
            onClick = {
                // Mock a new habit
                viewModel.addHabit("Mock Habit", 10)
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text("Add Habit")
        }
    }
}
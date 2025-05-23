package com.example.habittracker.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.habittracker.ui.components.DurationPicker
import com.example.habittracker.ui.viewmodels.HabitViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.habittracker.model.HabitEntity
import kotlinx.coroutines.flow.flowOf


@Composable
fun HabitFormScreen(
    navController: NavController,
    habitId: Int?,
    viewModel: HabitViewModel
) {
    val existingHabit = habitId?.let { id ->
        viewModel.habits.find { it.id == id }
    }
    val isEditing = habitId != null
    var title by remember { mutableStateOf(existingHabit?.title ?: "") }
    var duration by remember { mutableStateOf(existingHabit?.duration ?: 30) }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(Icons.Default.ArrowBack, contentDescription = "Back")
            }
            Text(
                text = "Habit Form",
                style = MaterialTheme.typography.headlineSmall
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        OutlinedTextField(
            value = title,
            onValueChange = { title = it },
            label = { Text("Title") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(16.dp))

        DurationPicker(
            duration = duration,
            onDurationChange = { duration = it },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.weight(1f))

        Button(
            onClick = {
                if (title.isNotBlank()) {
                    if (isEditing && habitId != null) {
                        viewModel.updateHabit(habitId, title.trim(), duration)
                    } else {
                        viewModel.addHabit(title.trim(), duration)
                    }
                    navController.popBackStack()
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp),
        ) {
            Text("Save")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewHabitFormScreen() {
    val navController = rememberNavController()

    val fakeRepo = remember {
        object : com.example.habittracker.data.HabitRepository(
            dao = object : com.example.habittracker.data.HabitDao {
                override fun getAllHabits() = flowOf(emptyList<HabitEntity>())
                override suspend fun insertHabit(habit: HabitEntity) {}
                override suspend fun updateHabit(habit: HabitEntity) {}
                override suspend fun deleteHabit(habit: HabitEntity) {}
            }
        ) {}
    }

    val previewViewModel = remember { HabitViewModel(fakeRepo) }

    HabitFormScreen(
        navController = navController,
        habitId = null,
        viewModel = previewViewModel
    )
}

@Preview(
    name = "Dark Mode",
    showBackground = true,
    uiMode = android.content.res.Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun PreviewHabitFormScreenDark() {
    val navController = rememberNavController()

    val fakeRepo = remember {
        object : com.example.habittracker.data.HabitRepository(
            dao = object : com.example.habittracker.data.HabitDao {
                override fun getAllHabits() = flowOf(emptyList<HabitEntity>())
                override suspend fun insertHabit(habit: HabitEntity) {}
                override suspend fun updateHabit(habit: HabitEntity) {}
                override suspend fun deleteHabit(habit: HabitEntity) {}
            }
        ) {}
    }

    val previewViewModel = remember { HabitViewModel(fakeRepo) }

    HabitFormScreen(
        navController = navController,
        habitId = null,
        viewModel = previewViewModel
    )
}
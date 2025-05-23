package com.example.habittracker.ui.screens

import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.habittracker.model.Habit
import com.example.habittracker.ui.components.HabitCard
import com.example.habittracker.ui.viewmodels.HabitViewModel
import com.example.habittracker.ui.navigation.Routes
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun HabitListScreen(
    navController: NavController,
    viewModel: HabitViewModel,
    isDarkMode: Boolean,
    onToggleTheme: () -> Unit
) {
    val habits = viewModel.habits
    val context = LocalContext.current
    var habitToDelete by remember { mutableStateOf<Habit?>(null) }
    val coroutineScope = rememberCoroutineScope()

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(WindowInsets.safeDrawing.asPaddingValues())
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            IconButton(
                onClick = { onToggleTheme() },
                modifier = Modifier.align(Alignment.TopEnd)
            ) {
                Icon(
                    imageVector = if (isDarkMode) Icons.Filled.LightMode else Icons.Default.DarkMode,
                    contentDescription = "Toggle Theme"
                )
            }
        }

        val visibleHabitIds = remember { mutableStateListOf<Int>() }

        if (visibleHabitIds.isEmpty() && habits.isNotEmpty()) {
            visibleHabitIds.addAll(habits.map { it.id })
        }
        LazyColumn(modifier = Modifier.weight(1f)) {
            items(habits) { habit ->
                AnimatedVisibility(
                    visible = visibleHabitIds.contains(habit.id),
                    enter = fadeIn(animationSpec = tween(300)),
                    exit = fadeOut(animationSpec = tween(300))
                ) {
                    HabitCard(
                        habit = habit,
                        onClick = {},
                        onEdit = { navController.navigate("${Routes.HABIT_FORM}?habitId=${habit.id}") },
                        onDelete = { habitToDelete = habit },
                        onMarkComplete = {
                            viewModel.markComplete(habit.id)
                            Toast.makeText(context, "Habit marked as complete ✅", Toast.LENGTH_SHORT).show()
                        }
                    )
                }
            }
        }

        val interactionSource = remember { MutableInteractionSource() }
        val isPressed by interactionSource.collectIsPressedAsState()
        val scale by animateFloatAsState(
            targetValue = if (isPressed) 0.95f else 1f,
            animationSpec = tween(150),
            label = "buttonPressScale"
        )

        Button(
            onClick = { navController.navigate(Routes.HABIT_FORM) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .height(56.dp)
                .scale(scale),
            shape = RoundedCornerShape(12.dp),
            interactionSource = interactionSource
        ) {
            Text("Add Habit", fontSize = 16.sp)
        }

        habitToDelete?.let { habit ->
            AlertDialog(
                onDismissRequest = { habitToDelete = null },
                title = { Text("Delete Habit") },
                text = { Text("Are you sure you want to delete \"${habit.title}\"?") },
                confirmButton = {
                    TextButton(onClick = {
                        habitToDelete = null
                        visibleHabitIds.remove(habit.id) // triggers exit animation

                        coroutineScope.launch {
                            delay(300) // match your fadeOut duration
                            viewModel.deleteHabit(habit.id)
                        }
                    }) {
                        Text("Delete")
                    }
                },
                dismissButton = {
                    TextButton(onClick = { habitToDelete = null }) {
                        Text("Cancel")
                    }
                }
            )
        }
    }
}
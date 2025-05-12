package com.example.habittracker.ui.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.example.habittracker.model.Habit
import com.example.habittracker.model.HabitStatus

/**
 * This composable function renders a habit card with visual cues based on its status.
 *
 * - EMPTY: Displays an 'Add' icon to allow users to add a new habit.
 * - INCOMPLETE: Shows habit details and action buttons (Start, Edit, Complete, Delete).
 * - COMPLETE: Indicates the habit is completed with summary text.
 *
 * @param habit The [Habit] instance representing the current habit's data.
 * @param onClick Optional callback triggered when the card is clicked.
 */
@Composable
fun HabitCard(habit: Habit, onClick: () -> Unit = {}) {
    val cardColor by animateColorAsState(
        when (habit.status) {
            HabitStatus.EMPTY -> Color.LightGray
            HabitStatus.INCOMPLETE -> Color(0xFFFFCDD2)
            HabitStatus.COMPLETE -> Color(0xFFC8E6C9)
        },
        label = "cardColorAnimation"
    )

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onClick() },
        colors = CardDefaults.cardColors(containerColor = cardColor),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        when (habit.status) {
            HabitStatus.EMPTY -> EmptyHabitCard()
            HabitStatus.INCOMPLETE -> IncompleteHabitCard(habit)
            HabitStatus.COMPLETE -> CompleteHabitCard(habit)
        }
    }
}

/**
 * A composable UI element for an empty habit card.
 * Used as a placeholder to prompt the user to add a new habit.
 */
@Composable
fun EmptyHabitCard() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp),
        contentAlignment = Alignment.Center
    ) {
        Icon(Icons.Default.Add, contentDescription = "Add Habit", tint = Color.DarkGray)
    }
}

/**
 * Displays a card for an incomplete habit, including action buttons to manage it.
 *
 * @param habit The [Habit] containing title and duration data.
 */
@Composable
fun IncompleteHabitCard(habit: Habit) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(habit.title, fontSize = 18.sp, fontWeight = FontWeight.Bold)
        Text("Duration: ${habit.duration} mins")

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceAround) {
            IconButton(onClick = {}) { Icon(Icons.Default.PlayArrow, "Start") }
            IconButton(onClick = {}) { Icon(Icons.Default.Edit, "Edit") }
            IconButton(onClick = {}) { Icon(Icons.Default.Check, "Complete") }
            IconButton(onClick = {}) { Icon(Icons.Default.Delete, "Delete") }
        }
    }
}

/**
 * Renders a card representing a completed habit.
 *
 * @param habit The [Habit] to display.
 */
@Composable
fun CompleteHabitCard(habit: Habit) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(habit.title, fontSize = 18.sp, fontWeight = FontWeight.Bold)
        Text("Completed ✅ Duration: ${habit.duration} mins")
    }
}

@Preview
@Composable
fun IncompletePreview() {
    HabitCard(habit = Habit(1, "Workout", 45, HabitStatus.INCOMPLETE))
}


@Preview
@Composable
fun CompletePreview() {
    HabitCard(habit = Habit(1, "Meditate", 45, HabitStatus.COMPLETE))
}


@Preview
@Composable
fun EmptyPreview() {
    HabitCard(habit = Habit(0, status = HabitStatus.EMPTY))
}
package com.example.habittracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.habittracker.ui.screens.HabitList
import com.example.habittracker.ui.theme.HabitTrackerTheme

/**
 * Main entry point of the Habit Tracker application.
 *
 * Sets the Compose UI content and applies the app theme.
 */
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HabitTrackerTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    HabitList()
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    HabitTrackerTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            HabitList()
        }
    }
}
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
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.habittracker.ui.navigation.Routes
import com.example.habittracker.ui.screens.HabitFormScreen
import com.example.habittracker.ui.screens.HabitListScreen

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
            val navController = rememberNavController()

            HabitTrackerTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    NavHost(
                        navController = navController,
                        startDestination = Routes.HABIT_LIST
                    ) {
                        composable(Routes.HABIT_LIST) {
                            HabitListScreen(navController)
                        }
                        composable("${Routes.HABIT_FORM}?habitId={habitId}") { backStackEntry ->
                            val habitId = backStackEntry.arguments?.getString("habitId")?.toIntOrNull()
                            HabitFormScreen(navController, habitId)
                        }
                    }
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
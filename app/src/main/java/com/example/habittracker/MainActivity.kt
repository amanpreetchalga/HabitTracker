package com.example.habittracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.habittracker.ui.screens.HabitList
import com.example.habittracker.ui.theme.HabitTrackerTheme
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.habittracker.data.HabitDatabase
import com.example.habittracker.data.HabitRepository
import com.example.habittracker.ui.navigation.Routes
import com.example.habittracker.ui.screens.HabitFormScreen
import com.example.habittracker.ui.screens.HabitListScreen
import com.example.habittracker.ui.viewmodels.HabitViewModel
import com.example.habittracker.ui.viewmodels.HabitViewModelFactory

/**
 * Main entry point of the Habit Tracker application.
 *
 * Sets the Compose UI content and applies the app theme.
 */
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Create database, DAO, repo, ViewModel
        val database = HabitDatabase.getDatabase(applicationContext)
        val dao = database.habitDao()
        val repository = HabitRepository(dao)
        val factory = HabitViewModelFactory(repository)
        val viewModel = ViewModelProvider(this, factory)[HabitViewModel::class.java]

        setContent {
            val navController = rememberNavController()
            var isDarkMode by rememberSaveable { mutableStateOf(false) }

            HabitTrackerTheme(darkTheme = isDarkMode) {
                Surface(color = MaterialTheme.colorScheme.background) {
                    NavHost(
                        navController = navController,
                        startDestination = Routes.HABIT_LIST
                    ) {
                        composable(Routes.HABIT_LIST) {
                            HabitListScreen(
                                navController = navController,
                                viewModel = viewModel,
                                isDarkMode = isDarkMode,
                                onToggleTheme = { isDarkMode = !isDarkMode }
                            )
                        }
                        composable("${Routes.HABIT_FORM}?habitId={habitId}") { backStackEntry ->
                            val habitId = backStackEntry.arguments?.getString("habitId")?.toIntOrNull()
                            HabitFormScreen(navController, habitId, viewModel)
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
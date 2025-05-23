package com.example.habittracker.ui.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

data class HabitColors(
    val complete: Color,
    val incomplete: Color,
    val empty: Color
)

val LocalHabitColors = staticCompositionLocalOf<HabitColors> {
    error("No HabitColors provided")
}

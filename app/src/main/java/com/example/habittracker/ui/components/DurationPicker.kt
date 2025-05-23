package com.example.habittracker.ui.components

import android.widget.NumberPicker
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView

/**
 * A reusable composable that displays a scrollable NumberPicker
 * for selecting habit duration in minutes.
 *
 * @param duration The currently selected duration value.
 * @param onDurationChange Callback when the user changes the duration.
 */
@Composable
fun DurationPicker(
    duration: Int,
    onDurationChange: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Text("Duration (mins)", style = MaterialTheme.typography.labelLarge)
        Spacer(modifier = Modifier.height(4.dp))

        AndroidView(
            factory = { context ->
                NumberPicker(context).apply {
                    minValue = 1
                    maxValue = 180
                    value = duration
                    setOnValueChangedListener { _, _, newVal ->
                        onDurationChange(newVal)
                    }
                }
            },
            update = { it.value = duration },
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
        )
    }
}
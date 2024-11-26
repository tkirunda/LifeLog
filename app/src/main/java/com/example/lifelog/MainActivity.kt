package com.example.lifelog

import android.os.Bundle
import android.text.format.DateFormat
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.lifelog.model.Feeling
import com.example.lifelog.ui.theme.LifeLogTheme
import com.example.lifelog.viewmodel.FeelingViewModel
import java.util.*
import androidx.compose.foundation.clickable
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults



// Top-level utility function
fun getTimeOfDayGreeting(currentTime: Long): String {
    val hour = Calendar.getInstance().apply { timeInMillis = currentTime }.get(Calendar.HOUR_OF_DAY)
    return when (hour) {
        in 0..11 -> "Good Morning!"
        in 12..17 -> "Good Afternoon!"
        else -> "Good Evening!"
    }
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LifeLogTheme {
                MainScreen()
            }
        }
    }
}

@Composable
fun MainScreen() {
    val currentTime = System.currentTimeMillis()
    val timeOfDayMessage = getTimeOfDayGreeting(currentTime)
    val currentFormattedTime = DateFormat.format("hh:mm a", Date(currentTime)).toString()

    var isLoggingEmotion by remember { mutableStateOf(false) }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
    ) { padding ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            if (isLoggingEmotion) {
                EmotionLoggingScreen(
                    onSave = { mood, category, notes ->
                        println("Saved: Mood: $mood, Category: $category, Notes: $notes")
                        isLoggingEmotion = false
                    },
                    onCancel = {
                        isLoggingEmotion = false
                    }
                )
            } else {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            text = timeOfDayMessage,
                            style = MaterialTheme.typography.headlineMedium,
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Button(onClick = {
                            isLoggingEmotion = true
                        }) {
                            Text(text = "Log Your Emotion")
                        }
                    }

                    Text(
                        text = "Current time: $currentFormattedTime",
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )
                }
            }
        }
    }
}

@Composable
fun EmotionLoggingScreen(
    onSave: (String, String, String) -> Unit,
    onCancel: () -> Unit
) {
    Column(modifier = Modifier.padding(16.dp)) {
        var mood by remember { mutableStateOf("") }
        var category by remember { mutableStateOf("") }
        var notes by remember { mutableStateOf("") }

        Text(text = "Log Your Emotion", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            value = mood,
            onValueChange = { mood = it },
            label = { Text("Mood") }
        )
        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            value = category,
            onValueChange = { category = it },
            label = { Text("Emotion Category") }
        )
        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            value = notes,
            onValueChange = { notes = it },
            label = { Text("Notes") }
        )
        Spacer(modifier = Modifier.height(16.dp))
        Row {
            Button(onClick = { onSave(mood, category, notes) }) {
                Text("Save")
            }
            Spacer(modifier = Modifier.width(8.dp))
            Button(onClick = onCancel) {
                Text("Cancel")
            }
        }
    }
}

@Composable
fun MoodDropdown(selectedMood: String, onMoodSelected: (String) -> Unit) {
    var expanded by remember { mutableStateOf(false) }
    val moods = listOf("Happy", "Sad", "Excited", "Angry")

    Box(modifier = Modifier.wrapContentSize(Alignment.TopStart)) {
        Text(
            text = selectedMood.ifEmpty { "Select Mood" },
            modifier = Modifier.clickable(interactionSource = remember { MutableInteractionSource() }, indication = null) {
                expanded = true
            }
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            moods.forEach { mood ->
                DropdownMenuItem(
                    onClick = {
                        onMoodSelected(mood)
                        expanded = false
                    }
                ) {
                    Text(text = mood)
                }
            }
        }
    }
}

@Composable
fun EmotionChipGroup(selectedTags: List<String>, onTagSelected: (String) -> Unit) {
    val emotions = listOf("Joy", "Love", "Fear", "Anger", "Surprise")
    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
        emotions.forEach { emotion ->
            AssistChip(
                onClick = { onTagSelected(emotion) },
                label = { Text(text = emotion) },
                colors = AssistChipDefaults.assistChipColors()
            )
        }
    }
}

@Composable
fun EmotionLogger(feelingViewModel: FeelingViewModel, onCancel: () -> Unit) {
    EmotionLoggingScreen(
        onSave = { mood, emotionCategory, notes ->
            val newFeeling = Feeling(
                id = 0,
                date = System.currentTimeMillis(),
                mood = mood,
                emotionTags = listOf(emotionCategory),
                notes = notes,
                emotionCategory = emotionCategory,
                emotionDescriptor = "",
                promptResponse = ""
            )
            feelingViewModel.insertFeeling(newFeeling)
        },
        onCancel = onCancel
    )
}

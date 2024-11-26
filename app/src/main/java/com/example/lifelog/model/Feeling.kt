package com.example.lifelog.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Feeling")
data class Feeling(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val date: Long,
    val emotionCategory: String,      // New field for category
    val emotionDescriptor: String,    // New field for selected vocabulary word
    val mood: String, // e.g., "Happy", "Sad"
    val emotionTags: List<String>, // List of emotion tags
    val notes: String? = null,
    val promptResponse: String? = null // Optional response to guided prompts
    )
// Emotion.kt
package com.example.lifelog.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Emotion")
data class Emotion(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val category: String, // e.g., Joy, Sadness, Anger, etc.
    val description: String
)

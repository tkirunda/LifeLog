package com.example.lifelog.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "Letter")
data class Letter(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val date: Date,
    val mood: String,
    val emotionTags: List<String>,
    val notes: String?
)

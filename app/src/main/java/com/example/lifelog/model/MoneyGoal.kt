package com.example.lifelog.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "MoneyGoal")
data class MoneyGoal(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val goalName: String,       // Goal name
    val targetAmount: Double,   // Target amount
    val currentAmount: Double,  // Current amount saved
    val notes: String?,         // Additional notes
    val date: Date?          // Optional due date
)

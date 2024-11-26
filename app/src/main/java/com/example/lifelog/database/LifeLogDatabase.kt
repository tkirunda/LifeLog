package com.example.lifelog.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.lifelog.dao.FeelingDao
import com.example.lifelog.model.Converters
import com.example.lifelog.model.Emotion
import com.example.lifelog.model.Feeling
import com.example.lifelog.model.Letter
import com.example.lifelog.model.MoneyGoal
import com.example.lifelog.model.Reflection

@Database(
    entities = [Feeling::class, Reflection::class, MoneyGoal::class, Letter::class, Emotion::class],
    version = 2,
    exportSchema = false
)
@TypeConverters(Converters::class) // Include this only if you're using custom TypeConverters
abstract class LifeLogDatabase : RoomDatabase() {

    // Define DAOs as abstract functions
    abstract fun feelingDao(): FeelingDao
}

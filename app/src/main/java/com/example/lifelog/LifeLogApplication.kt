package com.example.lifelog

import android.app.Application
import androidx.room.Room
import com.example.lifelog.database.LifeLogDatabase

class LifeLogApplication : Application() {

    companion object {
        lateinit var database: LifeLogDatabase
            private set
    }

    override fun onCreate() {
        super.onCreate()

        // Initialize the Room database
        database = Room.databaseBuilder(
            applicationContext,
            LifeLogDatabase::class.java,
            "lifelog_database" // Database name
        )
            .fallbackToDestructiveMigration() // Optional: Use this during development to handle version upgrades
            .build()
    }
}

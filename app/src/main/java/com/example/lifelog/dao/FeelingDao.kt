package com.example.lifelog.dao

import androidx.room.*
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.lifelog.model.Emotion
import com.example.lifelog.model.Feeling
import kotlinx.coroutines.flow.Flow

@Dao
interface FeelingDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFeeling(feeling: Feeling)

    @Update
    suspend fun updateFeeling(feeling: Feeling)

    @Delete
    suspend fun deleteFeeling(feeling: Feeling)

    @Query("SELECT * FROM Feeling WHERE id = :id")
    fun getFeelingById(id: Int): Flow<Feeling> // Or `Feeling` directly if not asynchronous

    @Query("SELECT * FROM Feeling ORDER BY date DESC")
    fun getAllFeeling(): Flow<List<Feeling>>

    @Query("SELECT * FROM Emotion WHERE category = :category")
    suspend fun getEmotionsByCategory(category: String): List<Emotion>
}

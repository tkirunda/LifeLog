package com.example.lifelog.dao

import androidx.room.*
import com.example.lifelog.model.Reflection
import kotlinx.coroutines.flow.Flow

@Dao
interface ReflectionDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertReflection(reflection: Reflection)

    @Update
    suspend fun updateReflection(reflection: Reflection)

    @Delete
    suspend fun deleteReflection(reflection: Reflection)

    @Query("SELECT * FROM Reflection ORDER BY date DESC")
    fun getAllReflections(): Flow<List<Reflection>>
}

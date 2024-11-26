package com.example.lifelog.dao

import androidx.room.*
import com.example.lifelog.model.Letter
import kotlinx.coroutines.flow.Flow

@Dao
interface LetterDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLetter(letter: Letter)

    @Update
    suspend fun updateLetter(letter: Letter)

    @Delete
    suspend fun deleteLetter(letter: Letter)

    @Query("SELECT * FROM Letter ORDER BY date DESC")
    fun getAllLetters(): Flow<List<Letter>>
}

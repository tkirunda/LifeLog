package com.example.lifelog.dao

import androidx.room.*
import com.example.lifelog.model.MoneyGoal
import kotlinx.coroutines.flow.Flow

@Dao
interface MoneyGoalDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMoneyGoal(moneygoal: MoneyGoal)

    @Update
    suspend fun updateMoneyGoal(moneygoal: MoneyGoal)

    @Delete
    suspend fun deleteMoneyGoal(moneygoal: MoneyGoal)

    @Query("SELECT * FROM MoneyGoal ORDER BY date DESC")
    fun getAllMoneyGoals(): Flow<List<MoneyGoal>>
}

package com.example.lifelog.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lifelog.LifeLogApplication
import com.example.lifelog.dao.FeelingDao
import com.example.lifelog.model.Feeling
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class FeelingViewModel : ViewModel() {

    private val feelingDao: FeelingDao = LifeLogApplication.database.feelingDao()

    // Observe all feelings as a StateFlow
    val allFeelings: StateFlow<List<Feeling>> = feelingDao.getAllFeeling()
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    // Insert a new feeling
    fun insertFeeling(feeling: Feeling) {
        viewModelScope.launch {
            try {
                feelingDao.insertFeeling(feeling)
            } catch (e: Exception) {
                // Log the error or handle it as needed
                e.printStackTrace()
            }
        }

        // Update an existing feeling
        fun updateFeeling(feeling: Feeling) {
            viewModelScope.launch {
                feelingDao.updateFeeling(feeling)
            }
        }

        // Delete a feeling
        fun deleteFeeling(feeling: Feeling) {
            viewModelScope.launch {
                feelingDao.deleteFeeling(feeling)
            }
        }

        suspend fun getEmotionsByCategory(category: String) =
            feelingDao.getEmotionsByCategory(category)

        suspend fun getAllFeelings() = feelingDao.getAllFeeling()
    }
}


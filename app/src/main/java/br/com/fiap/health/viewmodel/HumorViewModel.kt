package br.com.fiap.health.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import br.com.fiap.health.data.AppDatabase
import br.com.fiap.health.model.Mood
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class HumorViewModel(application: Application) : AndroidViewModel(application) {

    private val moodDao = AppDatabase.getDatabase(application).moodDao()

    val moodList: StateFlow<List<Mood>> =
        moodDao.getAllMoods()
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    fun addMood(mood: String, note: String) {
        viewModelScope.launch {
            moodDao.insert(
                Mood(
                    mood = mood,
                    note = note
                )
            )
        }
    }
}

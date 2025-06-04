package br.com.fiap.health.repository

import br.com.fiap.health.api.RetrofitInstance
import br.com.fiap.health.data.MoodDao
import br.com.fiap.health.model.Mood
import kotlinx.coroutines.flow.Flow

class MoodRepository(private val moodDao: MoodDao) {

    val getAllMoods: Flow<List<Mood>> = moodDao.getAllMoods()

    suspend fun insert(mood: Mood) {
        moodDao.insert(mood)
        RetrofitInstance.api.postMood(mood)
    }

    suspend fun fetchMoodsFromApi(): List<Mood> {
        return RetrofitInstance.api.getMoods()
    }

    suspend fun delete(mood: Mood) {
        moodDao.delete(mood)
    }
}

package br.com.fiap.health.data

import androidx.room.*
import br.com.fiap.health.model.Mood
import kotlinx.coroutines.flow.Flow

@Dao
interface MoodDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(mood: Mood)

    @Query("SELECT * FROM mood_table ORDER BY timestamp DESC")
    fun getAllMoods(): Flow<List<Mood>>

    @Delete
    suspend fun delete(mood: Mood)
}

package br.com.fiap.health.api

import br.com.fiap.health.model.Mood
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Body

interface MoodApiService {
    @GET("moods")
    suspend fun getMoods(): List<Mood>

    @POST("moods")
    suspend fun postMood(@Body mood: Mood): Mood
}

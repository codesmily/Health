package br.com.fiap.health.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    val api: MoodApiService by lazy {
        Retrofit.Builder()
            .baseUrl("https://6830d2956205ab0d6c3a879f.mockapi.io/") //  API mockada
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MoodApiService::class.java)
    }
}

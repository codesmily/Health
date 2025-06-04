package br.com.fiap.waterguardapp.retrofit

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApiService {
    @GET("weather")
    suspend fun getWeather(
        @Query("q") city: String,            // Nome da cidade
        @Query("appid") apiKey: String,      // Sua chave da API
        @Query("units") units: String = "metric"  // Unidade da temperatura (Celsius)
    ): Response<WeatherResponse>

    @GET("forecast")
    suspend fun getForecast(
        @Query("q") city: String,
        @Query("appid") apiKey: String,
        @Query("units") units: String = "metric",
        @Query("lang") lang: String = "pt"
    ): Response<ForecastResponse>

}



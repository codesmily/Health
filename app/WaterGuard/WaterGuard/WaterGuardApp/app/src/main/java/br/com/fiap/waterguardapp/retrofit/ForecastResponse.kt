package br.com.fiap.waterguardapp.retrofit

data class ForecastResponse(
    val list: List<ForecastItem>
)

data class ForecastItem(
    val dt_txt: String, // Data e hora da previsão
    val main: MainData,
    val weather: List<WeatherDescription>
)

data class MainData(
    val temp: Double,
    val humidity: Int
)

data class WeatherDescription(
    val description: String
)

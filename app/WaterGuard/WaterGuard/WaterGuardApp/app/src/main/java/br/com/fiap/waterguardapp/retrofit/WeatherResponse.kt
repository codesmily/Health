package br.com.fiap.waterguardapp.retrofit

// Resposta completa que a API do OpenWeatherMap retorna
data class WeatherResponse(
    val weather: List<Weather>,
    val main: Main,
    val wind: Wind
)

data class Weather(
    val description: String // Descrição do clima, ex: "cloudy", "sunny"
)

data class Main(
    val temp: Float,       // Temperatura em Celsius
    val humidity: Int      // Umidade
)

data class Wind(
    val speed: Float       // Velocidade do vento em m/s
)

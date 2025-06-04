package com.fiap.waterguardapp.screen

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.com.fiap.waterguardapp.retrofit.RetrofitInstance
import br.com.fiap.waterguardapp.retrofit.ForecastResponse
import retrofit2.Response

@Composable
fun DashboardScreen(navController: NavController) {
    var forecastData by remember { mutableStateOf<ForecastResponse?>(null) }
    var isLoading by remember { mutableStateOf(true) }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    LaunchedEffect(Unit) {
        try {
            val response: Response<ForecastResponse> = RetrofitInstance.api.getForecast(
                city = "São Paulo",
                apiKey = "ce3bcf73e4685ba4f73d2511c409f250"
            )

            if (response.isSuccessful) {
                forecastData = response.body()
            } else {
                errorMessage = "Erro: ${response.code()}"
            }
        } catch (e: Exception) {
            errorMessage = "Falha na conexão: ${e.message}"
        }
        isLoading = false
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(Color(0xFF2196F3), Color(0xFFBBDEFB))
                )
            )
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Previsão do Tempo para seus Próximos Dias",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Aproveite a chuva para economizar! Colete para Irrigar plantas, lavar o quintal, etc.",
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
            color = Color.White,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))

        if (isLoading) {
            CircularProgressIndicator(color = Color.White)
        } else {
            forecastData?.let { forecast ->
                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    items(forecast.list.take(5)) { forecastItem ->
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp),
                            colors = CardDefaults.cardColors(containerColor = Color(0xFF90CAF9)),
                            elevation = CardDefaults.cardElevation(6.dp)
                        ) {
                            Column(
                                modifier = Modifier.padding(16.dp),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text("Data: ${forecastItem.dt_txt}", fontWeight = FontWeight.Bold, color = Color(0xFF1565C0))
                                Spacer(modifier = Modifier.height(4.dp))
                                Text("Temperatura: ${forecastItem.main.temp}°C", fontSize = 18.sp, color = Color.DarkGray)
                                Text("Umidade: ${forecastItem.main.humidity}%", fontSize = 18.sp, color = Color.DarkGray)
                                Text("Condição: ${forecastItem.weather[0].description}", fontSize = 18.sp, color = Color.DarkGray)
                            }
                        }
                    }
                }
            }
            errorMessage?.let {
                Text(text = it, color = Color.Red, fontSize = 18.sp, fontWeight = FontWeight.Bold)
            }
        }
    }
}

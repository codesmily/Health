package br.com.fiap.waterguardapp.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.background
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

data class QualidadeAgua(
    val ph: Double,
    val turbidez: Double,
    val temperatura: Double,
    val timestamp: Long
)

class QualidadeViewModel : ViewModel() {

    private val _qualidade = MutableStateFlow<QualidadeAgua?>(null)
    val qualidade: StateFlow<QualidadeAgua?> = _qualidade

    init {
        simularDadosEmTempoReal()
    }

    private fun simularDadosEmTempoReal() {
        viewModelScope.launch {
            while (true) {
                delay(3000) // Atualiza a cada 3 segundos
                _qualidade.value = QualidadeAgua(
                    ph = (6..9).random() + Math.random(),
                    turbidez = (0..100).random().toDouble(),
                    temperatura = (20..30).random().toDouble(),
                    timestamp = System.currentTimeMillis()
                )
            }
        }
    }
}

@Composable
fun QualidadeScreen(viewModel: QualidadeViewModel = viewModel()) {
    val qualidadeState by viewModel.qualidade.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
            .background(Color(0xFFE3F2FD)), // Fundo em azul claro
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            "*Qualidade da Água*",
            style = MaterialTheme.typography.headlineSmall.copy(color = Color(0xFF0288D1)) // Título em azul escuro
        )
        Spacer(modifier = Modifier.height(24.dp))

        qualidadeState?.let { qualidade ->
            Text(
                "pH: ${"%.2f".format(qualidade.ph)}",
                style = MaterialTheme.typography.bodyLarge.copy(color = Color(0xFF039BE5)) // Texto azul médio
            )
            Text(
                "Turbidez: ${"%.2f".format(qualidade.turbidez)} NTU",
                style = MaterialTheme.typography.bodyLarge.copy(color = Color(0xFF039BE5)) // Texto azul médio
            )
            Text(
                "Temperatura: ${"%.2f".format(qualidade.temperatura)} °C",
                style = MaterialTheme.typography.bodyLarge.copy(color = Color(0xFF039BE5)) // Texto azul médio
            )
            Text(
                "Última atualização: ${formatarDataHora(qualidade.timestamp)}",
                style = MaterialTheme.typography.bodyMedium.copy(color = Color(0xFF0288D1)) // Texto azul escuro
            )
        } ?: run {
            CircularProgressIndicator(
                color = Color(0xFF0288D1) // Indicador de carregamento azul escuro
            )
        }
    }
}

fun formatarDataHora(timestamp: Long): String {
    val sdf = SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.getDefault())
    return sdf.format(Date(timestamp))
}

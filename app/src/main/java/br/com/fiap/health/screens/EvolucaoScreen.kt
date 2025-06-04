package br.com.fiap.health.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.fiap.health.viewmodel.HumorViewModel

@Composable
fun MoodBar(
    label: String,
    value: Int,
    maxValue: Int
) {
    val barWidth = (value / maxValue.toFloat()).coerceIn(0f, 1f)

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.bodyLarge
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(24.dp)
                .background(
                    color = MaterialTheme.colorScheme.surfaceVariant,
                    shape = RoundedCornerShape(12.dp)
                )
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth(barWidth)
                    .fillMaxHeight()
                    .background(
                        color = MaterialTheme.colorScheme.primary,
                        shape = RoundedCornerShape(12.dp)
                    )
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EvolucaoScreen(viewModel: HumorViewModel) {
    val moodList by viewModel.moodList.collectAsState()

    val groupedMoods = moodList.groupingBy { it.mood }.eachCount()

    val maxCount = (groupedMoods.values.maxOrNull() ?: 1)

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Minha Evolução") },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(20.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            Text(
                text = "Evolução do seu bem-estar emocional 🧠",
                style = MaterialTheme.typography.titleLarge.copy(fontSize = 20.sp)
            )

            groupedMoods.forEach { (label, count) ->
                MoodBar(label, count, maxCount)
            }

            if (moodList.isEmpty()) {
                Text(
                    text = "Nenhum dado ainda. Faça um check-in para começar!",
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }
    }
}

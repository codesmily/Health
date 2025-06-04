package br.com.fiap.health.screens

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AvaliacaoScreen() {
    val context = LocalContext.current
    var showResultDialog by remember { mutableStateOf(false) }
    var resultado by remember { mutableStateOf("") }

    val perguntas = listOf(
        "Você se sente sobrecarregado frequentemente?",
        "Está com dificuldade para dormir ou relaxar?",
        "Tem se sentido ansioso, inquieto ou preocupado?",
        "Percebe mudanças no seu humor, como tristeza ou irritabilidade?",
        "Sente cansaço extremo, mesmo após descansar?",
        "Tem se isolado socialmente ou evitado interações?",
        "Percebe dificuldade em se concentrar ou tomar decisões?",
        "Sente falta de motivação para atividades do dia a dia?"
    )

    val respostas = remember { mutableStateListOf(*Array(perguntas.size) { false }) }

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            TopAppBar(
                title = { Text("Avaliação de Bem-Estar") },
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
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            // ✅ Descrição inicial
            Text(
                text = "🧠 Avaliação de Saúde Emocional\n\nResponda às perguntas abaixo de acordo com como você se sente. Este questionário é anônimo e serve para ajudá-lo(a) a refletir sobre seu bem-estar.",
                style = MaterialTheme.typography.bodyMedium
            )

            // ✅ Legenda
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Text("✔️ Sim", color = MaterialTheme.colorScheme.primary)
                Text("❌ Não", color = MaterialTheme.colorScheme.error)
            }

            // ✅ Perguntas
            perguntas.forEachIndexed { index, pergunta ->
                Card(
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = if (respostas[index])
                            MaterialTheme.colorScheme.tertiary.copy(alpha = 0.2f)
                        else
                            MaterialTheme.colorScheme.surface
                    ),
                    elevation = CardDefaults.cardElevation(4.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            respostas[index] = !respostas[index]
                        }
                ) {
                    Text(
                        text = pergunta,
                        modifier = Modifier.padding(20.dp),
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // ✅ Botão Finalizar
            Button(
                onClick = {
                    val risco = respostas.count { it }
                    resultado = when {
                        risco <= 2 -> "Baixo risco. Continue cuidando de você! 😊"
                        risco in 3..5 -> "Risco moderado. Atenção ao seu bem-estar. Procure descansar, praticar atividades relaxantes e, se possível, converse com alguém de confiança."
                        else -> "Alto risco. Recomendamos fortemente procurar apoio psicológico. Sua saúde mental é prioridade! 💙"
                    }
                    showResultDialog = true
                },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(14.dp)
            ) {
                Text("Ver Resultado")
            }

            // ✅ Botão para Enviar por Email
            OutlinedButton(
                onClick = {
                    enviarEmail(respostas, perguntas, context)
                },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(14.dp)
            ) {
                Text("Enviar Resultado por Email")
            }
        }

        // ✅ Resultado do Questionário
        if (showResultDialog) {
            AlertDialog(
                onDismissRequest = { showResultDialog = false },
                confirmButton = {
                    TextButton(onClick = { showResultDialog = false }) {
                        Text("OK")
                    }
                },
                title = { Text("Seu Resultado") },
                text = {
                    Text(resultado)
                }
            )
        }
    }
}

fun enviarEmail(respostas: List<Boolean>, perguntas: List<String>, context: android.content.Context) {
    val destinatario = "psicologa@empresa.com"
    val assunto = "Avaliação de Bem-Estar - Relatório Anônimo"

    val corpo = buildString {
        append("📋 Avaliação de Bem-Estar:\n\n")
        perguntas.forEachIndexed { index, pergunta ->
            val respostaTexto = if (respostas[index]) "✔️ Sim" else "❌ Não"
            append("$pergunta -> $respostaTexto\n")
        }

        val risco = respostas.count { it }
        val resultado = when {
            risco <= 2 -> "Baixo risco. Continue cuidando de você! 😊"
            risco in 3..5 -> "Risco moderado. Atenção ao seu bem-estar."
            else -> "Alto risco. Recomendamos apoio psicológico urgente."
        }

        append("\n🔍 Resultado: $resultado")
        append("\n\n🔒 Este envio é anônimo.\n")
    }

    val intent = Intent(Intent.ACTION_SENDTO).apply {
        data = Uri.parse("mailto:")
        putExtra(Intent.EXTRA_EMAIL, arrayOf(destinatario))
        putExtra(Intent.EXTRA_SUBJECT, assunto)
        putExtra(Intent.EXTRA_TEXT, corpo)
    }

    if (intent.resolveActivity(context.packageManager) != null) {
        context.startActivity(intent)
    }
}

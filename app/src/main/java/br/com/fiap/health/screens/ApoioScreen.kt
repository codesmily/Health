package br.com.fiap.health.screens

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ApoioScreen() {
    val context = LocalContext.current
    var showDialog by remember { mutableStateOf(false) }
    var showSnackbar by remember { mutableStateOf(false) }

    var showAlertDialog by remember { mutableStateOf(false) }
    val selectedReminders = remember { mutableStateListOf<String>() }

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            TopAppBar(
                title = { Text("Apoio & Bem-Estar") },
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
                .padding(horizontal = 20.dp, vertical = 16.dp)
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "✨ Conte com a gente! Aqui estão recursos de apoio e bem-estar. Tudo anônimo, leve e seguro.",
                style = MaterialTheme.typography.bodyLarge.copy(fontSize = 18.sp, fontWeight = FontWeight.Medium),
                color = MaterialTheme.colorScheme.onSurface
            )


            // Card - Fale com a Gente
            SupportCard(
                title = "Fale com a gente 💌",
                description = "Quer sugerir algo, relatar um problema ou enviar feedback? Fale com a gente de forma 100% anônima e segura.",
                icon = Icons.Default.Face,
                buttonText = "Abrir formulário",
                onClick = {
                    val url = Intent(Intent.ACTION_VIEW, Uri.parse("https://forms.office.com/Pages/ResponsePage.aspx?id=DQSIkWdsW0yxEjajBLZtrQAAAAAAAAAAAANAAf6hEsJUNTJaUktWUklUMUIwT0NCQTE3UjFDWUkzQy4u"))
                    context.startActivity(url)
                }
            )

            // Canal de Escuta
            SupportCard(
                title = "Falar com Alguém ❤️",
                description = "Converse de forma anônima e gratuita com voluntários. Atendimento 24h do CVV.",
                icon = Icons.Default.Phone,
                buttonText = "Ligar 188",
                onClick = {
                    val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:188"))
                    context.startActivity(intent)
                }
            )

            // Chat Online CVV
            SupportCard(
                title = "Chat Online 🧠",
                description = "Prefere digitar? Acesse o chat do CVV e converse online, 100% anônimo.",
                icon = Icons.Default.Face,
                buttonText = "Acessar Chat",
                onClick = {
                    val url = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.cvv.org.br/chat/"))
                    context.startActivity(url)
                }
            )



        }

        // Dialogo Feedback
        if (showDialog) {
            AlertDialog(
                onDismissRequest = { showDialog = false },
                confirmButton = {
                    TextButton(onClick = { showDialog = false }) {
                        Text("Fechar")
                    }
                },
                title = { Text("Feedback Anônimo") },
                text = {
                    Text(
                        "Este é um espaço para você sugerir melhorias ou compartilhar sua experiência no app. Estamos sempre buscando melhorar! Envie seu feedback através do formulário online:\n\nhttps://forms.gle/seulinkfake\n\nObrigado por colaborar! 💙"
                    )
                }
            )
        }

    }
}

@Composable
fun SupportCard(
    title: String,
    description: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    buttonText: String,
    onClick: () -> Unit
) {
    Card(
        shape = RoundedCornerShape(24.dp),
        elevation = CardDefaults.cardElevation(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        ),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .padding(20.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(40.dp)
                )
                Spacer(modifier = Modifier.width(16.dp))
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.SemiBold),
                    color = MaterialTheme.colorScheme.primary
                )
            }
            Text(
                text = description,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface
            )
            Button(
                onClick = onClick,
                modifier = Modifier.align(Alignment.End),
                shape = RoundedCornerShape(16.dp)
            ) {
                Text(buttonText)
            }
        }
    }
}

package br.com.fiap.health.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import android.widget.Toast
import br.com.fiap.health.viewmodel.HumorViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BemEstarScreen(viewModel: HumorViewModel) {
    val context = LocalContext.current
    var selectedMood by remember { mutableStateOf<String?>(null) }
    var note by remember { mutableStateOf("") }

    val moods = listOf("😄 Feliz", "😐 Neutro", "😟 Triste", "😠 Irritado", "😰 Ansioso")

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Check-in de Bem-Estar") },
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
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            Text(
                text = "Como você está se sentindo hoje?",
                style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.SemiBold)
            )

            moods.forEach { mood ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .selectable(
                            selected = (mood == selectedMood),
                            onClick = { selectedMood = mood },
                            role = Role.RadioButton
                        )
                        .padding(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        selected = (mood == selectedMood),
                        onClick = { selectedMood = mood }
                    )
                    Text(
                        text = mood,
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.padding(start = 8.dp)
                    )
                }
            }

            OutlinedTextField(
                value = note,
                onValueChange = { note = it },
                label = { Text("Quer escrever algo?") },
                modifier = Modifier.fillMaxWidth()
            )

            Button(
                onClick = {
                    if (selectedMood != null) {
                        viewModel.addMood(selectedMood!!, note)
                        Toast.makeText(
                            context,
                            "Check-in salvo! 😊",
                            Toast.LENGTH_SHORT
                        ).show()
                        selectedMood = null
                        note = ""
                    } else {
                        Toast.makeText(
                            context,
                            "Por favor, selecione como você se sente.",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                },
                modifier = Modifier.align(Alignment.End)
            ) {
                Text("Salvar")
            }
        }
    }
}



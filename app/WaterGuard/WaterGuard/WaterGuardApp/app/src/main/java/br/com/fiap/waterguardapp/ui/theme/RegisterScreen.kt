package br.com.fiap.waterguardapp.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import br.com.fiap.waterguardapp.UserViewModel

// Classe de simulação de banco de dados para armazenar os usuários
data class User(val username: String, val password: String)

val users = mutableListOf<User>() // Lista para simular o banco de dados

@Composable
fun RegisterScreen(navController: NavHostController, userViewModel: UserViewModel) {
    var username by remember { mutableStateOf(TextFieldValue("")) }
    var password by remember { mutableStateOf(TextFieldValue("")) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(Color(0xFFe3f2fd)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Cadastro",
            style = MaterialTheme.typography.headlineMedium,
            color = Color(0xFF2196F3)
        )

        Spacer(modifier = Modifier.height(32.dp))

        // Campo de nome de usuário
        BasicTextField(
            value = username,
            onValueChange = { username = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
                .background(Color.White)
                .padding(16.dp),
            singleLine = true,
            decorationBox = { innerTextField ->
                Box(Modifier.padding(16.dp)) {
                    if (username.text.isEmpty()) {
                        Text(text = "Username", color = Color.Gray)
                    }
                    innerTextField()
                }
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Campo de senha
        BasicTextField(
            value = password,
            onValueChange = { password = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
                .background(Color.White)
                .padding(16.dp),
            singleLine = true,
            decorationBox = { innerTextField ->
                Box(Modifier.padding(16.dp)) {
                    if (password.text.isEmpty()) {
                        Text(text = "Password", color = Color.Gray)
                    }
                    innerTextField()
                }
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Botão de registro
        Button(
            onClick = {
                userViewModel.addUser(username.text, password.text)
                navController.popBackStack() // Volta para a tela de login
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2196F3))
        ) {
            Text(
                text = "Cadastrar",
                style = MaterialTheme.typography.titleMedium,
                color = Color.White
            )
        }
    }
}


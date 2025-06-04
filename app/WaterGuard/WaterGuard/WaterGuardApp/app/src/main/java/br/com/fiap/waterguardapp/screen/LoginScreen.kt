package br.com.fiap.waterguardapp.screen

import android.os.Bundle
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

@Composable
fun LoginScreen(navController: NavHostController, userViewModel: UserViewModel) {
    var username by remember { mutableStateOf(TextFieldValue("")) }
    var password by remember { mutableStateOf(TextFieldValue("")) }
    var errorMessage by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) }

    // Função para realizar o login
    fun performLogin() {
        isLoading = true
        if (userViewModel.validateLogin(username.text, password.text)) {
            // Login bem-sucedido, navega para a próxima tela
            navController.navigate("navigation")  // Modifique para o nome correto da tela que vem depois
        } else {
            // Exibe erro de login
            errorMessage = "Usuário ou senha incorretos"
        }
        isLoading = false
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(Color(0xFFe3f2fd)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Bem-vindo ao WaterGuard!",
            style = MaterialTheme.typography.headlineLarge,
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

        // Exibe mensagem de erro, se houver
        if (errorMessage.isNotEmpty()) {
            Text(text = errorMessage, color = Color.Red)
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Botão de login
        Button(
            onClick = { performLogin() },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2196F3)),
            enabled = !isLoading
        ) {
            if (isLoading) {
                CircularProgressIndicator(color = Color.White, modifier = Modifier.size(24.dp))
            } else {
                Text(text = "Login", style = MaterialTheme.typography.titleMedium, color = Color.White)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Link para ir para a tela de cadastro
        TextButton(onClick = { navController.navigate("register") }) {
            Text(text = "Não tem uma conta? Registre-se aqui.")
        }
    }
}


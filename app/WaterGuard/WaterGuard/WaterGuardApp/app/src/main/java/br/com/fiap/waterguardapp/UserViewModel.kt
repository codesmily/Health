package br.com.fiap.waterguardapp

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

// ViewModel para armazenar a lista de usuários
class UserViewModel : ViewModel() {
    // Lista de usuários cadastrados
    val users = mutableStateOf<List<User>>(emptyList())

    // Função para adicionar um novo usuário
    fun addUser(username: String, password: String) {
        val newUser = User(username, password)
        users.value = users.value + newUser
    }

    // Função para verificar se o usuário e a senha são válidos
    fun validateLogin(username: String, password: String): Boolean {
        return users.value.any { it.username == username && it.password == password }
    }
}

// Dados do usuário
data class User(val username: String, val password: String)

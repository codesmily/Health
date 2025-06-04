package br.com.fiap.health.components


import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.compose.*
import br.com.fiap.health.screens.ApoioScreen
import br.com.fiap.health.screens.AvaliacaoScreen
import br.com.fiap.health.screens.BemEstarScreen
import br.com.fiap.health.screens.EvolucaoScreen
import br.com.fiap.health.viewmodel.HumorViewModel


@Composable
fun AppNavHost(viewModel: HumorViewModel) {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = { BottomNavigationBar(navController) }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "bemestar",
            modifier = Modifier.padding(innerPadding)
        ) {
            composable("bemestar") { BemEstarScreen(viewModel) }
            composable("apoio") { ApoioScreen() }
            composable("avaliacao") { AvaliacaoScreen() }
            composable("evolucao") { EvolucaoScreen(viewModel) }
        }
    }
}
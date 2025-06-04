package br.com.fiap.waterguardapp.screen

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.com.fiap.waterguardapp.WaterGuardApp

import com.fiap.waterguardapp.screen.DashboardScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "menu") {
        composable("menu") { WaterGuardApp(navController) }
        composable("dashboard") { DashboardScreen(navController) }

        composable("qualidade") { QualidadeScreen() }
        composable("dicas") { DicasScreen() }
        composable("impacto") { ImpactoScreen() }
    }
}
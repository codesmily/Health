package br.com.fiap.waterguardapp

import android.os.Bundle
import android.view.Menu
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.*
import br.com.fiap.waterguardapp.ui.theme.WaterGuardAppTheme
import br.com.fiap.waterguardapp.screen.DicasScreen

import br.com.fiap.waterguardapp.screen.QualidadeScreen
import br.com.fiap.waterguardapp.screen.ImpactoScreen
import br.com.fiap.waterguardapp.screen.LoginScreen
import br.com.fiap.waterguardapp.screen.Navigation
import br.com.fiap.waterguardapp.screen.RegisterScreen
import com.fiap.waterguardapp.screen.DashboardScreen

@Composable
fun WaterGuardApp(navController: NavHostController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF1976D2), // Azul forte
                        Color(0xFF42a5f5), // Azul claro
                        Color(0xFF81d4fa)  // Azul mais suave
                    )
                )
            )
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Lista de botões com texto
            val buttons = listOf(
                "Previsão" to "dashboard",
                "Qualidade da Água" to "qualidade",
                "Dicas" to "dicas",
                "Impacto" to "impacto"
            )

            buttons.forEach { (text, route) ->
                Button(
                    onClick = { navController.navigate(route) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp)
                        .padding(8.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.White
                    ),
                    shape = RoundedCornerShape(12.dp),
                    elevation = ButtonDefaults.buttonElevation(8.dp)
                ) {
                    Text(
                        text = text,
                        style = MaterialTheme.typography.titleMedium,
                        color = Color(0xFF1976D2) // Azul mais forte para o texto
                    )
                }
            }
        }
    }
}


@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val userViewModel: UserViewModel = viewModel()

    NavHost(navController = navController, startDestination = "login") {
        composable("login") {
            LoginScreen(navController = navController, userViewModel = userViewModel)
        }
        composable("register") {
            RegisterScreen(navController = navController, userViewModel = userViewModel)
        }
        composable("navigation") {
            // Aqui, o usuário será redirecionado para a tela de navegação após o login
            Navigation()  // Tela de navegação com várias opções
        }
    }
}





class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WaterGuardAppTheme {
                AppNavigation() // Configuração de navegação
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DefaultPreview() {
    WaterGuardAppTheme {
        Navigation() // Preview da navegação
    }
}

package br.com.fiap.health.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.navigation.NavController
import androidx.navigation.compose.*


@Composable
fun BottomNavigationBar(navController: NavController) {
    val items = listOf(
        NavItem("Bem-Estar", Icons.Default.ThumbUp, "bemestar"),
        NavItem("Apoio", Icons.Default.Star, "apoio"),
        NavItem("Avaliação", Icons.Default.Info, "avaliacao"),
        NavItem("Evolução", Icons.Default.Person, "evolucao")
    )

    NavigationBar {
        val backStack by navController.currentBackStackEntryAsState()
        items.forEach { item ->
            NavigationBarItem(
                icon = { Icon(item.icon, contentDescription = item.label) },
                label = { Text(item.label) },
                selected = backStack?.destination?.route == item.route,
                onClick = {
                    navController.navigate(item.route) {
                        popUpTo(navController.graph.startDestinationId) { saveState = true }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}

data class NavItem(val label: String, val icon: androidx.compose.ui.graphics.vector.ImageVector, val route: String)
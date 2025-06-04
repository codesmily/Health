package br.com.fiap.health.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color



// 🎨 Paleta Claro
private val LightColors = lightColorScheme(
    primary = Azul,
    onPrimary = Color.White,
    secondary = Rosa,
    onSecondary = Color.White,
    tertiary = Roxo,
    onTertiary = Color.White,
    background = FundoClaro,
    surface = Color.White,
    onSurface = TextoPrimarioClaro
)

// 🎨 Paleta Escuro
private val DarkColors = darkColorScheme(
    primary = AzulEscuro,
    onPrimary = Color.White,
    secondary = RosaEscuro,
    onSecondary = Color.White,
    tertiary = RoxoEscuro,
    onTertiary = Color.White,
    background = FundoEscuro,
    surface = Color(0xFF1E1E1E),
    onSurface = TextoPrimarioEscuro
)

@Composable
fun HealthAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) DarkColors else LightColors

    MaterialTheme(
        colorScheme = colors,
        typography = Typography(),
        content = content
    )
}

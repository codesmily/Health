package br.com.fiap.health

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import br.com.fiap.health.components.AppNavHost
import br.com.fiap.health.ui.theme.HealthAppTheme
import br.com.fiap.health.viewmodel.AppViewModelFactory
import br.com.fiap.health.viewmodel.HumorViewModel

class MainActivity : ComponentActivity() {

    private val viewModel: HumorViewModel by viewModels {
        AppViewModelFactory(application)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HealthAppTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    AppNavHost(viewModel = viewModel)
                }
            }
        }
    }
}

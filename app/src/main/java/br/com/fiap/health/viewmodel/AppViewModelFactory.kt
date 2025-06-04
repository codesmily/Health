package br.com.fiap.health.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class AppViewModelFactory(private val application: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HumorViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return HumorViewModel(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

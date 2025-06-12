package com.example.modul5.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.modul5.data.ThemePreferences
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class SettingsViewModel(app: Application) : AndroidViewModel(app) {
    private val prefs = ThemePreferences(app.applicationContext)

    val darkMode: Flow<Boolean> = prefs.darkModeFlow


    fun toggleDarkMode() {
        viewModelScope.launch {
            val current = darkMode.first()
            prefs.saveDarkModeSetting(!current)
        }
    }
}

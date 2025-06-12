package com.example.modul5.presentation.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.modul5.presentation.theme.Modul5Theme
import com.example.modul5.presentation.viewmodel.DinoViewModel
import com.example.modul5.presentation.viewmodel.DinoViewModelFactory
import com.example.modul5.presentation.viewmodel.SettingsViewModel
import androidx.compose.ui.platform.LocalContext

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val context = LocalContext.current
            val navController = rememberNavController()

            val viewModel: DinoViewModel = viewModel(factory = DinoViewModelFactory(context))
            val settingsViewModel: SettingsViewModel = viewModel()
            val isDarkMode by settingsViewModel.darkMode.collectAsState(initial = false)

            Modul5Theme(darkTheme = isDarkMode) {
                Scaffold { padding ->
                    NavHost(
                        navController = navController,
                        startDestination = "dino_list",
                        modifier = Modifier.padding(padding)
                    ) {
                        composable("dino_list") {
                            DinoListScreen(navController, viewModel, settingsViewModel, isDarkMode)
                        }
                        composable("detail") {

                            DinoDetailScreen(viewModel, navController)
                        }
                    }
                }
            }
        }
    }
}

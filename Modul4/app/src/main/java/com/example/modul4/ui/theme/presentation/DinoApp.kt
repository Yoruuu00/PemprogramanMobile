package com.example.modul4.ui.theme.presentation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.modul4.presentation.ui.screen.DinoDetailScreen
import com.example.modul4.presentation.ui.screen.DinoListScreen
import com.example.modul4.presentation.viewmodel.DinoViewModel
import com.example.modul4.presentation.viewmodel.DinoViewModelFactory

@Composable
fun DinoApp() {

    val navController: NavHostController = rememberNavController()

    val viewModel: DinoViewModel = viewModel(factory = DinoViewModelFactory())


    NavHost(navController = navController, startDestination = "dino_list") {
        composable("dino_list") {
            DinoListScreen(navController = navController, viewModel = viewModel)
        }

        composable("detail") {
            DinoDetailScreen(viewModel = viewModel, navController = navController)
        }
    }
}
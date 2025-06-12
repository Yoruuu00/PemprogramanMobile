package com.example.modul3.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.modul3.presentation.dino_detail.DinoDetailScreen
import com.example.modul3.presentation.dino_list.DinoListScreen
import java.net.URLDecoder
import java.nio.charset.StandardCharsets

@Composable
fun AppNavigation(navController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(
        navController = navController,
        startDestination = "dino_list",
        modifier = modifier
    ) {
        composable("dino_list") {
            DinoListScreen(navController)
        }
        composable("detail/{name}/{desc}/{imgRes}") { backStack ->
            val name = backStack.arguments?.getString("name") ?: ""
            val encodedDesc = backStack.arguments?.getString("desc") ?: ""
            val desc = URLDecoder.decode(encodedDesc, StandardCharsets.UTF_8.toString())
            val imgRes = backStack.arguments?.getString("imgRes")?.toIntOrNull()

            if (imgRes != null) {
                DinoDetailScreen(name, desc, imgRes, navController)
            } else {
                
            }
        }
    }
}
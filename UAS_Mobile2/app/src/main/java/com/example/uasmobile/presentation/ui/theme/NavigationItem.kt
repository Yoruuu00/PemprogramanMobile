package com.example.uasmobile.presentation.ui.theme

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector

sealed class NavigationItem(val route: String, val icon: ImageVector, val title: String) {
    object Home : NavigationItem("home_screen", Icons.Default.Home, "Beranda")
    object Genre : NavigationItem("genre_list_screen", Icons.Default.Search, "Jelajahi")
    object Favorite : NavigationItem("favorite_screen", Icons.Default.Favorite, "Favorit")
}
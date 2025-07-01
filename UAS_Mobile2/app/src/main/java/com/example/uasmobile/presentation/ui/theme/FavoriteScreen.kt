package com.example.uasmobile.presentation.ui.theme

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.uasmobile.presentation.component.ArtistCard
import com.example.uasmobile.presentation.viewModel.FavoriteViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoriteScreen(
    navController: NavController,
    viewModel: FavoriteViewModel
) {
    val favoriteArtists by viewModel.favoriteArtists.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Koleksi Favorit", style = MaterialTheme.typography.titleLarge) }
            )
        }
    ) { paddingValues ->
        if (favoriteArtists.isEmpty()) {
            Box(
                modifier = Modifier.fillMaxSize().padding(paddingValues),
                contentAlignment = Alignment.Center
            ) {
                Text("Anda belum punya artis favorit.", style = MaterialTheme.typography.bodyLarge)
            }
        } else {
            LazyVerticalGrid(
                columns = GridCells.Adaptive(minSize = 160.dp),
                modifier = Modifier.padding(paddingValues),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(favoriteArtists) { artist ->
                    ArtistCard(artist = artist) {
                        navController.navigate("detail_screen/${artist.id}")
                    }
                }
            }
        }
    }
}
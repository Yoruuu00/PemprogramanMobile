package com.example.tugasmobile

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    private val viewModel: SongViewModel by viewModels()

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
                val songs by viewModel.songs.collectAsState()

                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = { Text("Daftar Lagu") }
                        )
                    }
                ) { padding ->
                    LazyColumn(
                        contentPadding = padding,
                        modifier = Modifier.padding(16.dp)
                    ) {
                        items(songs) { song ->
                            SongItem(song)
                            Spacer(modifier = Modifier.height(8.dp))
                        }
                    }
                }
            }
        }

    }

@Composable
fun SongItem(song: Song) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "Title: ${song.title}")
            Text(text = "Artist: ${song.artist}")
            Text(text = "Genre: ${song.genre}")
            Text(text = "Duration: ${song.duration}")
            Text(text = "Favorite: ${if (song.favorite) "Yes" else "No"}")
        }
    }
}

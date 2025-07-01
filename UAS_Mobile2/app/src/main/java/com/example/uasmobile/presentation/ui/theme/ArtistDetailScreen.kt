package com.example.uasmobile.presentation.ui.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Stop
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.uasmobile.domain.model.Track
import com.example.uasmobile.presentation.component.AlbumCard
import com.example.uasmobile.presentation.component.TrackItem
import com.example.uasmobile.presentation.viewModel.ArtistDetailViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArtistDetailScreen(
    navController: NavController,
    viewModel: ArtistDetailViewModel
) {
    val state by viewModel.state.collectAsState()
    val albums by viewModel.albums.collectAsState()
    val topTracks by viewModel.topTracks.collectAsState()
    val isFavorite by viewModel.isFavorite.collectAsState()
    val playingTrackId by viewModel.playingTrackId.collectAsState()

    val context = LocalContext.current
    val exoPlayer = remember { ExoPlayer.Builder(context).build() }

    val playerListener = remember {
        object : Player.Listener {
            override fun onPlaybackStateChanged(playbackState: Int) {
                if (playbackState == Player.STATE_ENDED) {
                    viewModel.stopPlayback()
                }
            }
        }
    }

    DisposableEffect(Unit) {
        exoPlayer.addListener(playerListener)
        onDispose {
            exoPlayer.removeListener(playerListener)
            exoPlayer.release()
        }
    }

    LaunchedEffect(playingTrackId) {
        val trackId = playingTrackId
        val trackToPlay = topTracks.find { it.id == trackId }
        if (trackId == null || trackToPlay == null) {
            if (exoPlayer.isPlaying) exoPlayer.stop()
            exoPlayer.clearMediaItems()
            return@LaunchedEffect
        }

        val mediaItem = MediaItem.fromUri(trackToPlay.previewUrl)
        exoPlayer.setMediaItem(mediaItem)
        exoPlayer.prepare()
        exoPlayer.playWhenReady = true
    }

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            TopAppBar(
                title = { },
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, "Kembali", tint = Color.White)
                    }
                },
                actions = {
                    IconButton(onClick = { viewModel.toggleFavorite() }) {
                        Icon(
                            imageVector = if (isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                            contentDescription = "Favorit",
                            tint = if (isFavorite) Color.Red else Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Transparent)
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = paddingValues.calculateBottomPadding())
        ) {
            when {
                state.isLoading -> CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                state.error != null -> Text(state.error!!, modifier = Modifier.align(Alignment.Center))
                state.artist != null -> {
                    val artist = state.artist!!
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .verticalScroll(rememberScrollState())
                    ) {
                        Box(modifier = Modifier.fillMaxWidth().height(300.dp)) {
                            AsyncImage(
                                model = ImageRequest.Builder(LocalContext.current).data(artist.artworkUrl).crossfade(true).build(),
                                contentDescription = "Artist Artwork",
                                contentScale = ContentScale.Crop,
                                modifier = Modifier.fillMaxSize()
                            )
                            Box(
                                modifier = Modifier.fillMaxSize().background(
                                    Brush.verticalGradient(
                                        colors = listOf(Color.Transparent, MaterialTheme.colorScheme.background),

                                        startY = 300f,
                                    )
                                )
                            )
                            Text(
                                text = artist.name,
                                style = MaterialTheme.typography.displaySmall,
                                fontWeight = FontWeight.Bold,
                                color = Color.White,
                                modifier = Modifier.align(Alignment.BottomStart).padding(16.dp)
                            )
                        }

                        Column(modifier = Modifier.padding(16.dp)) {
                            if (topTracks.isNotEmpty()) {
                                Text("Lagu Populer", style = MaterialTheme.typography.titleMedium)
                                Spacer(modifier = Modifier.height(8.dp))
                                topTracks.forEachIndexed { index, track ->
                                    val isPlaying = track.id == playingTrackId
                                    TrackItem(
                                        track = track,
                                        trackNumber = index + 1,
                                        isPlaying = isPlaying,
                                        onClick = { viewModel.onTrackClicked(track.id) }
                                    )
                                }
                            }

                            Spacer(modifier = Modifier.height(24.dp))

                            Text("Album", style = MaterialTheme.typography.titleMedium, modifier = Modifier.padding(bottom = 8.dp))
                            LazyRow(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                                items(albums) { album ->
                                    AlbumCard(
                                        album = album,
                                        onClick = { navController.navigate("album_detail/${album.id}") }
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
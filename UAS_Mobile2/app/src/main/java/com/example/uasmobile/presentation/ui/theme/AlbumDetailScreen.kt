package com.example.uasmobile.presentation.ui.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.uasmobile.presentation.component.TrackItem
import com.example.uasmobile.presentation.viewModel.AlbumDetailViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AlbumDetailScreen(
    navController: NavController,
    viewModel: AlbumDetailViewModel
) {
    val state by viewModel.state.collectAsState()
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

    LaunchedEffect(playingTrackId, state.albumDetail) {
        val trackId = playingTrackId
        val tracks = state.albumDetail?.tracks ?: emptyList()
        if (trackId == null) {
            if (exoPlayer.isPlaying) exoPlayer.stop()
            exoPlayer.clearMediaItems()
            return@LaunchedEffect
        }
        val trackToPlay = tracks.find { it.id == trackId }
        if (trackToPlay != null) {
            val mediaItem = MediaItem.fromUri(trackToPlay.previewUrl)
            exoPlayer.setMediaItem(mediaItem)
            exoPlayer.prepare()
            exoPlayer.playWhenReady = true
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        state.albumDetail?.title ?: "Detail Album",
                        style = MaterialTheme.typography.titleLarge,
                        maxLines = 1,
                        overflow = androidx.compose.ui.text.style.TextOverflow.Ellipsis
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Kembali")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface
                )
            )
        },
        containerColor = MaterialTheme.colorScheme.background
    ) { paddingValues ->
        Box(modifier = Modifier.fillMaxSize().padding(paddingValues)) {
            when {
                state.isLoading -> CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                state.error != null -> Text("Error: ${state.error}", modifier = Modifier.align(Alignment.Center))
                state.albumDetail != null -> {
                    val detail = state.albumDetail!!
                    Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
                        Box(modifier = Modifier.fillMaxWidth().height(300.dp)) {
                            AsyncImage(
                                model = ImageRequest.Builder(LocalContext.current).data(detail.coverUrl).crossfade(true).build(),
                                contentDescription = detail.title,
                                contentScale = ContentScale.Crop,
                                modifier = Modifier.fillMaxSize()
                            )
                            Box(
                                modifier = Modifier.fillMaxSize().background(
                                    Brush.verticalGradient(
                                        colors = listOf(Color.Transparent, MaterialTheme.colorScheme.background.copy(alpha = 0.8f), MaterialTheme.colorScheme.background),
                                        startY = 200f
                                    )
                                )
                            )
                            Column(
                                modifier = Modifier.align(Alignment.BottomStart).padding(16.dp)
                            ) {
                                Text(detail.title, style = MaterialTheme.typography.headlineMedium, color = MaterialTheme.colorScheme.onBackground, fontWeight = FontWeight.Bold)
                                Text(
                                    text = "Album by ${detail.artistName}",
                                    style = MaterialTheme.typography.titleMedium,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                                    modifier = Modifier.clickable {
                                        navController.navigate("detail_screen/${detail.artistId}")
                                    }
                                )
                            }
                        }
                        Column(modifier = Modifier.padding(16.dp)) {
                            detail.tracks.forEachIndexed { index, track ->
                                val isPlaying = track.id == playingTrackId
                                TrackItem(
                                    track = track,
                                    trackNumber = index + 1,
                                    isPlaying = isPlaying,
                                    onClick = { viewModel.onTrackClicked(track.id) }
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
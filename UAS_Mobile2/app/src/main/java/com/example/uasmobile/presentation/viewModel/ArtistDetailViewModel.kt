package com.example.uasmobile.presentation.viewModel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.uasmobile.domain.model.Album
import com.example.uasmobile.domain.model.Artist
import com.example.uasmobile.domain.model.Track
import com.example.uasmobile.domain.repository.MusicRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.net.UnknownHostException

data class ArtistDetailState(
    val isLoading: Boolean = false,
    val artist: Artist? = null,
    val error: String? = null
)

class ArtistDetailViewModel(
    private val repository: MusicRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = MutableStateFlow(ArtistDetailState())
    val state: StateFlow<ArtistDetailState> = _state.asStateFlow()

    private val _albums = MutableStateFlow<List<Album>>(emptyList())
    val albums: StateFlow<List<Album>> = _albums.asStateFlow()

    private val _topTracks = MutableStateFlow<List<Track>>(emptyList())
    val topTracks: StateFlow<List<Track>> = _topTracks.asStateFlow()

    private val _playingTrackId = MutableStateFlow<String?>(null)
    val playingTrackId: StateFlow<String?> = _playingTrackId.asStateFlow()

    val isFavorite: StateFlow<Boolean>

    init {
        val artistId = savedStateHandle.get<String>("artistId") ?: ""
        isFavorite = repository.isArtistFavorite(artistId)
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), false)

        if (artistId.isNotBlank()) {
            loadInitialArtistData(artistId)
        }
    }

    private fun loadInitialArtistData(artistId: String) {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true)
            repository.getArtistById(artistId)
                .onSuccess { artist ->
                    if (artist != null) {
                        _state.value = _state.value.copy(artist = artist)
                        loadAlbumsAndTopTracks(artist.id)
                    } else {
                        _state.value = _state.value.copy(isLoading = false, error = "Artis tidak ditemukan")
                    }
                }
                .onFailure { error ->
                    val errorMessage = if (error is UnknownHostException) {
                        "Tidak ada koneksi internet. Silakan periksa jaringan Anda."
                    } else {
                        "Terjadi kesalahan: ${error.message}"
                    }
                    _state.value = _state.value.copy(isLoading = false, error = errorMessage)
                }
        }
    }

    private fun loadAlbumsAndTopTracks(artistId: String) {
        viewModelScope.launch {
            repository.getAlbumsByArtist(artistId).onSuccess { albumList ->
                _albums.value = albumList
                if (albumList.isNotEmpty()) {
                    val allTracksDeferred = albumList.map { album ->
                        async { repository.getAlbumDetail(album.id).getOrNull()?.tracks ?: emptyList() }
                    }
                    val allTracks = allTracksDeferred.awaitAll().flatten()
                    _topTracks.value = allTracks.shuffled().take(10)
                }
            }.onFailure {
                _albums.value = emptyList()
                _topTracks.value = emptyList()
            }
            _state.value = _state.value.copy(isLoading = false)
        }
    }

    fun onTrackClicked(trackId: String) {
        if (_playingTrackId.value == trackId) {
            _playingTrackId.value = null
        } else {
            _playingTrackId.value = trackId
        }
    }

    fun stopPlayback() {
        _playingTrackId.value = null
    }

    fun toggleFavorite() {
        viewModelScope.launch {
            val artist = state.value.artist ?: return@launch
            if (isFavorite.value) {
                repository.removeArtistFromFavorites(artist)
            } else {
                repository.addArtistToFavorites(artist)
            }
        }
    }
}
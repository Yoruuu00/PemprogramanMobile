package com.example.uasmobile.presentation.viewModel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.uasmobile.domain.model.AlbumDetail
import com.example.uasmobile.domain.model.Track
import com.example.uasmobile.domain.repository.MusicRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class AlbumDetailState(
    val isLoading: Boolean = false,
    val albumDetail: AlbumDetail? = null,
    val error: String? = null
)

class AlbumDetailViewModel(
    private val repository: MusicRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = MutableStateFlow(AlbumDetailState())
    val state: StateFlow<AlbumDetailState> = _state.asStateFlow()

    private val _playingTrackId = MutableStateFlow<String?>(null)
    val playingTrackId: StateFlow<String?> = _playingTrackId.asStateFlow()

    init {

        savedStateHandle.get<String>("albumId")?.let { albumId ->
            if (albumId.isNotBlank()) {
                loadAlbumDetail(albumId)
            }
        }
    }

    private fun loadAlbumDetail(albumId: String) {
        viewModelScope.launch {
            _state.value = AlbumDetailState(isLoading = true)
            repository.getAlbumDetail(albumId)
                .onSuccess { detail ->
                    _state.value = AlbumDetailState(albumDetail = detail)
                }
                .onFailure { error ->
                    _state.value = AlbumDetailState(error = error.message)
                }
        }
    }

    // Fungsi untuk play/pause lagu
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
}
package com.example.uasmobile.presentation.viewModel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.uasmobile.domain.model.Artist
import com.example.uasmobile.domain.repository.MusicRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class GenreDetailState(
    val isLoading: Boolean = false,
    val artists: List<Artist> = emptyList(),
    val error: String? = null
)

class GenreDetailViewModel(
    private val repository: MusicRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = MutableStateFlow(GenreDetailState())
    val state: StateFlow<GenreDetailState> = _state.asStateFlow()

    init {
        savedStateHandle.get<String>("genreId")?.let { genreId ->
            loadArtistsByGenre(genreId)
        }
    }

    private fun loadArtistsByGenre(genreId: String) {
        viewModelScope.launch {
            _state.value = GenreDetailState(isLoading = true)
            repository.getArtistsByGenre(genreId)
                .onSuccess { artists ->
                    _state.value = GenreDetailState(artists = artists)
                }
                .onFailure { error ->
                    _state.value = GenreDetailState(error = error.message)
                }
        }
    }
}
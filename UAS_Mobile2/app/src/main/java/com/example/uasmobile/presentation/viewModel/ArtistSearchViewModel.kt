package com.example.uasmobile.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.uasmobile.domain.model.Artist
import com.example.uasmobile.domain.usecase.SearchArtistsUseCase
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

data class ArtistSearchState(
    val isLoading: Boolean = false,
    val artists: List<Artist> = emptyList(),
    val error: String? = null,
    val searchQuery: String = ""
)

sealed interface ArtistSearchEvent {
    data class OnSearchQueryChange(val query: String) : ArtistSearchEvent
}

class ArtistSearchViewModel(
    private val searchArtistsUseCase: SearchArtistsUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(ArtistSearchState())
    val state: StateFlow<ArtistSearchState> = _state.asStateFlow()

    private var searchJob: Job? = null

    fun onEvent(event: ArtistSearchEvent) {
        when (event) {
            is ArtistSearchEvent.OnSearchQueryChange -> {
                _state.update { it.copy(searchQuery = event.query) }

                searchJob?.cancel()
                searchJob = viewModelScope.launch {
                    delay(400) // debounce
                    searchArtist(event.query)
                }
            }
        }
    }

    private fun searchArtist(name: String) {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true, error = null) }

            val result = searchArtistsUseCase(name)
            result
                .onSuccess { artists ->
                    _state.update {
                        it.copy(
                            isLoading = false,
                            artists = artists,
                            error = if (artists.isEmpty()) "Artis tidak ditemukan" else null
                        )
                    }
                }
                .onFailure { e ->
                    _state.update {
                        it.copy(
                            isLoading = false,
                            artists = emptyList(),
                            error = "Terjadi kesalahan: ${e.message}"
                        )
                    }
                }
        }
    }
}

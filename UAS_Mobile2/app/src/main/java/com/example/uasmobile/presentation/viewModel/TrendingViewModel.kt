package com.example.uasmobile.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.uasmobile.domain.model.Album
import com.example.uasmobile.domain.model.Artist
import com.example.uasmobile.domain.usecase.GetChartsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class TrendingState(
    val artists: List<Artist> = emptyList(),
    val albums: List<Album> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)

class TrendingViewModel(
    private val getChartsUseCase: GetChartsUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(TrendingState())
    val state: StateFlow<TrendingState> = _state.asStateFlow()

    init {
        loadTrendingData()
    }

    private fun loadTrendingData() {
        _state.value = _state.value.copy(isLoading = true)
        viewModelScope.launch {
            val result = getChartsUseCase()
            result
                .onSuccess { (artists, albums) ->
                    _state.value = TrendingState(
                        artists = artists,
                        albums = albums,
                        isLoading = false
                    )
                }
                .onFailure { e ->
                    _state.value = TrendingState(
                        isLoading = false,
                        error = e.message ?: "Terjadi kesalahan"
                    )
                }
        }
    }
}

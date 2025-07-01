package com.example.uasmobile.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.uasmobile.domain.model.Genre
import com.example.uasmobile.domain.repository.MusicRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.net.UnknownHostException

data class GenreState(
    val isLoading: Boolean = false,
    val genres: List<Genre> = emptyList(),
    val error: String? = null
)

class GenreViewModel(private val repository: MusicRepository) : ViewModel() {
    private val _state = MutableStateFlow(GenreState())
    val state: StateFlow<GenreState> = _state.asStateFlow()

    init {
        loadGenres()
    }

    private fun loadGenres() {
        viewModelScope.launch {
            _state.value = GenreState(isLoading = true)
            repository.getGenres()
                .onSuccess { genres ->
                    _state.value = GenreState(genres = genres.filter { it.id.toLong() != 0L })
                }
                .onFailure { error ->
                    val errorMessage = when (error) {
                        is UnknownHostException -> "Tidak ada koneksi internet. Silakan periksa jaringan Anda."
                        else -> "Terjadi kesalahan: ${error.message}"
                    }
                    _state.value = GenreState(error = errorMessage)
                }
        }
    }
}
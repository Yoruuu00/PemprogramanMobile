package com.example.uasmobile.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.uasmobile.domain.model.Album
import com.example.uasmobile.domain.model.Artist
import com.example.uasmobile.domain.usecase.SearchAlbumsUseCase
import com.example.uasmobile.domain.usecase.SearchArtistsUseCase
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.net.UnknownHostException

data class SearchResultState(
    val isLoading: Boolean = true,
    val artists: List<Artist> = emptyList(),
    val albums: List<Album> = emptyList(),
    val error: String? = null
)

class SearchResultViewModel(
    private val searchArtistsUseCase: SearchArtistsUseCase,
    private val searchAlbumsUseCase: SearchAlbumsUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(SearchResultState())
    val state = _state.asStateFlow()

    fun searchAll(query: String) {
        viewModelScope.launch {
            _state.value = SearchResultState(isLoading = true)

            val artistsDeferred = async { searchArtistsUseCase(query) }
            val albumsDeferred = async { searchAlbumsUseCase(query) }

            val artistsResult = artistsDeferred.await()
            val albumsResult = albumsDeferred.await()

            artistsResult.onSuccess { artists ->
                _state.value = _state.value.copy(artists = artists)
            }
            artistsResult.onFailure { e ->
                if (e is UnknownHostException) {
                    _state.value = _state.value.copy(
                        isLoading = false,
                        error = "Tidak ada koneksi internet. Silakan periksa jaringan Anda."
                    )
                } else {
                    _state.value = _state.value.copy(
                        isLoading = false,
                        error = e.message ?: "Terjadi kesalahan saat mencari artis."
                    )
                }
            }


            albumsResult.onSuccess { albums ->
                _state.value = _state.value.copy(albums = albums)
            }

            albumsResult.onFailure { e ->
                if (e is UnknownHostException) {

                    if (_state.value.error == null || !_state.value.error!!.contains("Tidak ada koneksi internet")) {
                        _state.value = _state.value.copy(
                            isLoading = false,
                            error = "Tidak ada koneksi internet. Silakan periksa jaringan Anda."
                        )
                    }
                } else {
                    if (_state.value.error == null) {
                        _state.value = _state.value.copy(
                            isLoading = false,
                            error = e.message ?: "Terjadi kesalahan saat mencari album."
                        )
                    }
                }
            }

            if (_state.value.error == null) {
                val finalError = artistsResult.exceptionOrNull()?.message ?: albumsResult.exceptionOrNull()?.message
                _state.value = _state.value.copy(isLoading = false, error = finalError)
            } else {
                _state.value = _state.value.copy(isLoading = false)
            }
        }
    }
}
package com.example.uasmobile.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.uasmobile.domain.repository.MusicRepository
import com.example.uasmobile.domain.usecase.GetArtistDetailsUseCase
import com.example.uasmobile.domain.usecase.GetChartsUseCase
import com.example.uasmobile.domain.usecase.SearchAlbumsUseCase
import com.example.uasmobile.domain.usecase.SearchArtistsUseCase

class ViewModelFactory(
    private val searchArtistsUseCase: SearchArtistsUseCase,
    private val searchAlbumsUseCase: SearchAlbumsUseCase,
    private val getArtistDetailsUseCase: GetArtistDetailsUseCase,
    private val musicRepository: MusicRepository,
    private val getChartsUseCase: GetChartsUseCase
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
        return when {
            modelClass.isAssignableFrom(SearchResultViewModel::class.java) -> {
                SearchResultViewModel(searchArtistsUseCase, searchAlbumsUseCase) as T
            }
            modelClass.isAssignableFrom(ArtistDetailViewModel::class.java) -> {
                val savedStateHandle = extras.createSavedStateHandle()
                ArtistDetailViewModel(musicRepository, savedStateHandle) as T
            }
            modelClass.isAssignableFrom(TrendingViewModel::class.java) -> {
                TrendingViewModel(getChartsUseCase) as T
            }
            modelClass.isAssignableFrom(GenreViewModel::class.java) -> {
                GenreViewModel(musicRepository) as T
            }
            modelClass.isAssignableFrom(GenreDetailViewModel::class.java) -> {
                val savedStateHandle = extras.createSavedStateHandle()
                GenreDetailViewModel(musicRepository, savedStateHandle) as T
            }
            modelClass.isAssignableFrom(FavoriteViewModel::class.java) -> {
                FavoriteViewModel(musicRepository) as T
            }
            modelClass.isAssignableFrom(AlbumDetailViewModel::class.java) -> {
                val savedStateHandle = extras.createSavedStateHandle()
                AlbumDetailViewModel(musicRepository, savedStateHandle) as T
            }
            else -> throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
        }
    }
}
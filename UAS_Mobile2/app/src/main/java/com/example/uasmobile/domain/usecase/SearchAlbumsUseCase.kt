package com.example.uasmobile.domain.usecase

import com.example.uasmobile.domain.model.Album
import com.example.uasmobile.domain.repository.MusicRepository

class SearchAlbumsUseCase(
    private val repository: MusicRepository
) {
    suspend operator fun invoke(query: String): Result<List<Album>> {
        return repository.searchAlbums(query)
    }
}
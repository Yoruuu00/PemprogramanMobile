package com.example.uasmobile.domain.usecase

import com.example.uasmobile.domain.model.Artist
import com.example.uasmobile.domain.repository.MusicRepository

class SearchArtistsUseCase(
    private val repository: MusicRepository
) {
    suspend operator fun invoke(name: String): Result<List<Artist>> {
        return repository.searchArtists(name)
    }
}

package com.example.uasmobile.domain.usecase

import com.example.uasmobile.domain.model.Artist
import com.example.uasmobile.domain.repository.MusicRepository

class GetArtistDetailsUseCase(private val repository: MusicRepository) {
    suspend operator fun invoke(artistName: String): Result<Artist?> {
        return repository.getArtistDetails(artistName)
    }
}
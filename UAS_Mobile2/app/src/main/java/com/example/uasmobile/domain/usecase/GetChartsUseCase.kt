package com.example.uasmobile.domain.usecase

import com.example.uasmobile.domain.model.Album
import com.example.uasmobile.domain.model.Artist
import com.example.uasmobile.domain.repository.MusicRepository

class GetChartsUseCase(private val repository: MusicRepository) {
    suspend operator fun invoke(): Result<Pair<List<Artist>, List<Album>>> {
        return repository.getCharts()
    }
}
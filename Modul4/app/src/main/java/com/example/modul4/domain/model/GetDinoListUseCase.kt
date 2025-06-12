package com.example.modul4.domain.usecase

import com.example.modul4.domain.model.Dino
import com.example.modul4.domain.repository.DinoRepository
import kotlinx.coroutines.flow.Flow

class SearchDinosUseCase(private val repository: DinoRepository) {
    operator fun invoke(query: String): Flow<List<Dino>> =
        repository.searchDinos(query)
}

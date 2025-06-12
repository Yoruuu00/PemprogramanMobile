package com.example.modul3.domain.usecase

import com.example.modul3.domain.model.Dinosaur
import com.example.modul3.domain.repository.DinosaurRepository

class GetDinosaursUseCase(private val repository: DinosaurRepository) {
    operator fun invoke(): List<Dinosaur> {
        return repository.getDinosaurs()
    }
}
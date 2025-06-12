package com.example.modul3.data.repository

import com.example.modul3.data.datasource.LocalDinosaurDataSource
import com.example.modul3.domain.model.Dinosaur
import com.example.modul3.domain.repository.DinosaurRepository


class DinosaurRepositoryImpl : DinosaurRepository {
    override fun getDinosaurs(): List<Dinosaur> {
        return LocalDinosaurDataSource.getDinosaurs()
    }
}
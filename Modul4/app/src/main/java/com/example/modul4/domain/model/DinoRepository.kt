package com.example.modul4.domain.repository

import com.example.modul4.domain.model.Dino
import kotlinx.coroutines.flow.Flow

interface DinoRepository {
    fun getAllDinos(): Flow<List<Dino>>
    fun searchDinos(query: String): Flow<List<Dino>>
}

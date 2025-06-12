package com.example.modul5.domain.repository

import com.example.modul5.data.Resource
import com.example.modul5.domain.Dino
import kotlinx.coroutines.flow.Flow

interface DinoRepository {
    fun getAllDinos(): Flow<Resource<List<Dino>>>

    fun selectDino(dino: Dino)
    fun getSelectedDino(): Dino?
}
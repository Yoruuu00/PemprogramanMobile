package com.example.modul3.domain.repository

import com.example.modul3.domain.model.Dinosaur

interface DinosaurRepository {
    fun getDinosaurs(): List<Dinosaur>
}
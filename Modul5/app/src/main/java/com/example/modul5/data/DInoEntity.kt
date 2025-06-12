package com.example.modul5.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.modul5.domain.Dino

@Entity(tableName = "dinos")
data class DinoEntity(
    @PrimaryKey val name: String,
    val description: String,
    val imageUrl: String,
    val diet: String,
    val period: String
)

fun DinoEntity.toDomain() = Dino(
    name, description, imageUrl, diet, period
)

fun Dino.toEntity() = DinoEntity(
    name, description, imageUrl, diet, period
)
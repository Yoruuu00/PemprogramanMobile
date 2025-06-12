package com.example.modul5.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import com.example.modul5.domain.Dino

@Serializable
data class DinoDto(
    val name: String,
    val description: String,
    val diet: String,
    val period: String,
    val image: String
) {
    fun toDomain() = Dino(name, description, image, diet, period)
}

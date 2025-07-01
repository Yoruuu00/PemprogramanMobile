package com.example.uasmobile.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DeezerGenresResponse(
    val data: List<DeezerGenreDto>
)

@Serializable
data class DeezerGenreDto(
    val id: Long,
    val name: String,
    @SerialName("picture_medium") val pictureMedium: String
)
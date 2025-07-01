package com.example.uasmobile.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DeezerArtistDto(
    val id: Long,
    val name: String,
    @SerialName("picture_medium") val pictureMedium: String?,
    @SerialName("picture_xl") val pictureXL: String?
)
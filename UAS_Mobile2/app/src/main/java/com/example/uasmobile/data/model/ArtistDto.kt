package com.example.uasmobile.data.model

import com.example.uasmobile.domain.model.Artist
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DeezerSearchResponse(
    val data: List<DeezerArtistDto>
)


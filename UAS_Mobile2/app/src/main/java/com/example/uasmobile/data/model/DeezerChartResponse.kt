package com.example.uasmobile.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DeezerChartResponse(
    val artists: DeezerArtistsResponse,
    val albums: DeezerAlbumsResponse
)

@Serializable
data class DeezerArtistsResponse(
    val data: List<DeezerArtistDto>
)


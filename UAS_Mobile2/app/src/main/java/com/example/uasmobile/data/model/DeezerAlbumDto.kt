package com.example.uasmobile.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class DeezerAlbumsResponse(
    val data: List<DeezerAlbumDto>
)

@Serializable
data class DeezerAlbumDto(
    val id: Long,
    val title: String,
    @SerialName("cover_medium") val coverUrl: String?,
    val artist: DeezerArtistDto? = null
)

@Serializable
data class DeezerAlbumDetailResponse(
    val id: Long,
    val title: String,
    @SerialName("cover_big") val coverUrl: String,
    val artist: DeezerArtistDto,
    val tracks: Tracks
)

@Serializable
data class Tracks(
    val data: List<DeezerTrackDto>
)

@Serializable
data class DeezerTrackDto(
    val id: Long,
    val title: String,
    @SerialName("preview") val previewUrl: String,
    val duration: Int
)
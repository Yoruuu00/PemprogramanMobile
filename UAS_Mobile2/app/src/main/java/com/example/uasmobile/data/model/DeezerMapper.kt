package com.example.uasmobile.data.model

import com.example.uasmobile.domain.model.Album
import com.example.uasmobile.domain.model.AlbumDetail
import com.example.uasmobile.domain.model.Artist
import com.example.uasmobile.domain.model.Genre
import com.example.uasmobile.domain.model.Track

private fun formatDuration(totalSeconds: Int): String {
    if (totalSeconds < 0) return "0:00"
    val minutes = totalSeconds / 60
    val seconds = totalSeconds % 60
    return String.format("%d:%02d", minutes, seconds)
}

fun DeezerAlbumDto.toDomain() = Album(
    id = id.toString(),
    title = title,
    coverUrl = coverUrl ?:""
)

fun DeezerTrackDto.toDomain() = Track(
    id = id.toString(),
    title = title,
    previewUrl = previewUrl,
    duration = formatDuration(duration)
)

fun DeezerArtistDto.toDomain() = Artist(
    id = id.toString(),
    name = name,
    genre = null,
    picture = pictureMedium?:"",
    artworkUrl = pictureXL ?: pictureMedium ?:""
)

fun DeezerGenreDto.toDomain() = Genre(
    id = id.toString(),
    name = name,
    pictureUrl = pictureMedium
)

fun DeezerAlbumDetailResponse.toDomain() = AlbumDetail(
    id = id.toString(),
    title = title,
    coverUrl = coverUrl,
    artistName = artist.name,
    artistId = artist.id.toString(),
    tracks = tracks.data.map { it.toDomain() }
)
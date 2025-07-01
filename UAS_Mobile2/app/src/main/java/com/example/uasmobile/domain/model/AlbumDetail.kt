package com.example.uasmobile.domain.model

data class AlbumDetail(
    val id: String,
    val title: String,
    val coverUrl: String,
    val artistName: String,
    val artistId: String,
    val tracks: List<Track>
)
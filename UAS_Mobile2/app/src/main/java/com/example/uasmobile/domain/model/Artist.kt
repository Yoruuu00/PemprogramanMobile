package com.example.uasmobile.domain.model

data class Artist(
    val id: String,
    val name: String,
    val genre: String?,
    val picture: String,
    val artworkUrl: String?
)
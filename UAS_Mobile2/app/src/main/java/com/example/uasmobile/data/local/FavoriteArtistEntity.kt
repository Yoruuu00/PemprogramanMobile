package com.example.uasmobile.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_artists")
data class FavoriteArtistEntity(
    @PrimaryKey val id: String,
    val name: String,
    val pictureUrl: String
)
package com.example.uasmobile.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteArtistDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(artist: FavoriteArtistEntity)

    @Delete
    suspend fun delete(artist: FavoriteArtistEntity)

    @Query("SELECT * FROM favorite_artists ORDER BY name ASC")
    fun getAllFavoriteArtists(): Flow<List<FavoriteArtistEntity>>

    @Query("SELECT EXISTS(SELECT 1 FROM favorite_artists WHERE id = :artistId)")
    fun isFavorite(artistId: String): Flow<Boolean>
}
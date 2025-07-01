package com.example.uasmobile.domain.repository

import com.example.uasmobile.domain.model.Album
import com.example.uasmobile.domain.model.AlbumDetail
import com.example.uasmobile.domain.model.Artist
import com.example.uasmobile.domain.model.Track
import com.example.uasmobile.domain.model.Genre
import kotlinx.coroutines.flow.Flow

interface MusicRepository {

    suspend fun searchArtists(name: String): Result<List<Artist>>
    suspend fun getArtistDetails(name: String): Result<Artist?>
    suspend fun getAlbumsByArtist(artistId: String): Result<List<Album>>
    suspend fun getCharts(): Result<Pair<List<Artist>, List<Album>>>
    suspend fun searchAlbums(query: String): Result<List<Album>>
    suspend fun getArtistById(id: String): Result<Artist?>
    suspend fun getTrendingArtists(): Result<List<Artist>>
    suspend fun getTrendingAlbums(): Result<List<Album>>
    suspend fun getGenres(): Result<List<Genre>>
    suspend fun getArtistsByGenre(genreId: String): Result<List<Artist>>
    suspend fun addArtistToFavorites(artist: Artist)
    suspend fun removeArtistFromFavorites(artist: Artist)
    suspend fun getAlbumDetail(albumId: String): Result<AlbumDetail>
    fun getFavoriteArtists(): Flow<List<Artist>>
    fun isArtistFavorite(artistId: String): Flow<Boolean>
}
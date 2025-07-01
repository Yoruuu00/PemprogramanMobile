package com.example.uasmobile.data.repository

import com.example.uasmobile.data.local.FavoriteArtistDao
import com.example.uasmobile.data.local.FavoriteArtistEntity
import com.example.uasmobile.data.model.toDomain
import com.example.uasmobile.data.remote.DeezerApi
import com.example.uasmobile.domain.model.Album
import com.example.uasmobile.domain.model.AlbumDetail
import com.example.uasmobile.domain.model.Artist
import com.example.uasmobile.domain.model.Genre
import com.example.uasmobile.domain.model.Track
import com.example.uasmobile.domain.repository.MusicRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class MusicRepositoryImpl(
    private val api: DeezerApi,
    private val favoriteArtistDao: FavoriteArtistDao
) : MusicRepository {

    override suspend fun searchArtists(name: String): Result<List<Artist>> {
        return try {
            val response = api.searchArtistByName(name)
            val artists = response.data.map { it.toDomain() }
            Result.success(artists)
        } catch (e: Exception) {
            e.printStackTrace()
            Result.failure(e)
        }
    }
    override suspend fun searchAlbums(query: String): Result<List<Album>> {
        return try {
            val response = api.searchAlbumsByName(query)
            val albums = response.data.map { it.toDomain() }
            Result.success(albums)
        } catch (e: Exception) {
            e.printStackTrace()
            Result.failure(e)
        }
    }

    override suspend fun getArtistDetails(name: String): Result<Artist?> {
        return try {
            val response = api.searchArtistByName(name)
            val artist = response.data.firstOrNull()?.toDomain()
            Result.success(artist)
        } catch (e: Exception) {
            e.printStackTrace()
            Result.failure(e)
        }
    }

    override suspend fun getAlbumsByArtist(artistId: String): Result<List<Album>> {
        return try {
            val res = api.getAlbumsByArtistId(artistId.toLong())
            Result.success(res.data.map { it.toDomain() })
        } catch (e: Exception) {
            e.printStackTrace()
            Result.failure(e)
        }
    }

    override suspend fun getAlbumDetail(albumId: String): Result<AlbumDetail> {
        return try {
            val res = api.getAlbumDetails(albumId.toLong())
            Result.success(res.toDomain())
        } catch (e: Exception) {
            e.printStackTrace()
            Result.failure(e)
        }
    }

    override suspend fun getCharts(): Result<Pair<List<Artist>, List<Album>>> {
        return try {
            val res = api.getCharts()
            val artists = res.artists.data.map { it.toDomain() }
            val albums = res.albums.data.map { it.toDomain() }
            Result.success(artists to albums)
        } catch (e: Exception) {
            e.printStackTrace()
            Result.failure(e)
        }
    }

    override suspend fun getTrendingArtists(): Result<List<Artist>> {
        return try {
            val res = api.getCharts()
            val artists = res.artists.data.map { it.toDomain() }
            Result.success(artists)
        } catch (e: Exception) {
            e.printStackTrace()
            Result.failure(e)
        }
    }

    override suspend fun getTrendingAlbums(): Result<List<Album>> {
        return try {
            val res = api.getCharts()
            val albums = res.albums.data.map { it.toDomain() }
            Result.success(albums)
        } catch (e: Exception) {
            e.printStackTrace()
            Result.failure(e)
        }
    }

    override suspend fun getArtistById(id: String): Result<Artist?> {
        return try {
            val artistDto = api.getArtistById(id.toLong())
            Result.success(artistDto.toDomain())
        } catch (e: Exception) {
            e.printStackTrace()
            Result.failure(e)
        }
    }

    override suspend fun getGenres(): Result<List<Genre>> {
        return try {
            val res = api.getGenres()
            Result.success(res.data.map { it.toDomain() })
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getArtistsByGenre(genreId: String): Result<List<Artist>> {
        return try {
            val res = api.getArtistsByGenreId(genreId.toLong())
            Result.success(res.data.map { it.toDomain() })
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override fun getFavoriteArtists(): Flow<List<Artist>> {
        return favoriteArtistDao.getAllFavoriteArtists().map { entities ->
            entities.map {
                Artist(id = it.id, name = it.name, picture = it.pictureUrl, artworkUrl = it.pictureUrl, genre = null)
            }
        }
    }

    override fun isArtistFavorite(artistId: String): Flow<Boolean> {
        return favoriteArtistDao.isFavorite(artistId)
    }

    override suspend fun addArtistToFavorites(artist: Artist) {
        val entity = FavoriteArtistEntity(id = artist.id, name = artist.name, pictureUrl = artist.picture)
        favoriteArtistDao.insert(entity)
    }

    override suspend fun removeArtistFromFavorites(artist: Artist) {
        val entity = FavoriteArtistEntity(id = artist.id, name = artist.name, pictureUrl = artist.picture)
        favoriteArtistDao.delete(entity)
    }
}
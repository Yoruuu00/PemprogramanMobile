package com.example.uasmobile.data.remote

import com.example.uasmobile.data.model.DeezerAlbumDetailResponse
import com.example.uasmobile.data.model.DeezerAlbumsResponse
import com.example.uasmobile.data.model.DeezerArtistDto
import com.example.uasmobile.data.model.DeezerArtistsResponse
import com.example.uasmobile.data.model.DeezerChartResponse
import com.example.uasmobile.data.model.DeezerGenresResponse
import com.example.uasmobile.data.model.DeezerSearchResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface DeezerApi {
    @GET("search/artist")
    suspend fun searchArtistByName(@Query("q") name: String): DeezerSearchResponse

    @GET("search/album")
    suspend fun searchAlbumsByName(@Query("q") query: String): DeezerAlbumsResponse

    @GET("artist/{id}")
    suspend fun getArtistById(@Path("id") artistId: Long): DeezerArtistDto

    @GET("artist/{id}/albums")
    suspend fun getAlbumsByArtistId(@Path("id") artistId: Long): DeezerAlbumsResponse

    @GET("album/{id}")
    suspend fun getAlbumDetails(@Path("id") albumId: Long): DeezerAlbumDetailResponse

    @GET("chart/0")
    suspend fun getCharts(): DeezerChartResponse

    @GET("genre")
    suspend fun getGenres(): DeezerGenresResponse

    @GET("genre/{id}/artists")
    suspend fun getArtistsByGenreId(@Path("id") genreId: Long): DeezerArtistsResponse

}
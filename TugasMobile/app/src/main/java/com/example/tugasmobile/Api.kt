package com.example.tugasmobile

import retrofit2.http.GET

interface ApiService {
    @GET("/songs")
    suspend fun getSongs(): List<Song>
}

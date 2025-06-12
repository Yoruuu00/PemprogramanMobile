package com.example.modul5.data

import com.example.modul5.data.model.DinoDto
import retrofit2.http.GET

interface DinoApiService {
    @GET("api/dinosaurs")
    suspend fun getDinos(): List<DinoDto>
}

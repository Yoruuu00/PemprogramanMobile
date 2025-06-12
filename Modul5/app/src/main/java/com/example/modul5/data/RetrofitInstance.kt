package com.example.modul5.data

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import com.example.modul5.data.DinoApiService
import com.example.modul5.data.model.DinoDto


object RetrofitInstance {
    private val json = Json { ignoreUnknownKeys = true }
    private val contentType = "application/json".toMediaType()

    val api: DinoApiService by lazy {
        Retrofit.Builder()
            .baseUrl("https://dinoapi.brunosouzadev.com/")
            .addConverterFactory(json.asConverterFactory(contentType))
            .build()
            .create(DinoApiService::class.java)
    }
}

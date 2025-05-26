package com.example.tugasmobile

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SongViewModel : ViewModel() {
    private val _songs = MutableStateFlow<List<Song>>(emptyList())
    val songs: StateFlow<List<Song>> = _songs

    init {
        fetchSongs()
    }

    private fun fetchSongs() {
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.api.getSongs()
                Log.d("SongViewModel", "Fetched songs: $response")
                _songs.value = response
            } catch (e: Exception) {
                Log.e("SongViewModel", "Error: ${e.message}")
            }
        }
    }
}


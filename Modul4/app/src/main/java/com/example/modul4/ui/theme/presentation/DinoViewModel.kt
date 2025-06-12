package com.example.modul4.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.modul4.data.repository.DinoRepositoryImpl
import com.example.modul4.domain.model.Dino
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


class DinoViewModel : ViewModel() {

    private val repository = DinoRepositoryImpl()

    private val _dinoList = MutableStateFlow<List<Dino>>(emptyList())
    val dinoList: StateFlow<List<Dino>> = _dinoList

    init {
        viewModelScope.launch {
            repository.getAllDinos().collect {
                _dinoList.value = it
                Log.d("DinoViewModel", "Data dinosaurus berhasil dimuat: ${it.size} item.")
            }
        }
    }


    private val _selectedDino = MutableStateFlow<Dino?>(null)
    val selectedDino: StateFlow<Dino?> = _selectedDino
    fun selectDino(dino: Dino) {
        _selectedDino.value = dino
        Log.d("DinoViewModel", "Dino dipilih: ${dino.name}")
    }
}
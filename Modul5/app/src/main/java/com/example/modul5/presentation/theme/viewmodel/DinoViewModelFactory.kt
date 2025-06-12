package com.example.modul5.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.modul5.data.Resource
import com.example.modul5.domain.Dino
import com.example.modul5.domain.repository.DinoRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class DinoViewModel(private val repository: DinoRepository) : ViewModel() {


    private val _dinoListState = MutableStateFlow<Resource<List<Dino>>>(Resource.Loading())
    val dinoListState: StateFlow<Resource<List<Dino>>> = _dinoListState.asStateFlow()


    private val _selectedDino = MutableStateFlow<Dino?>(null)
    val selectedDino: StateFlow<Dino?> = _selectedDino.asStateFlow()

    init {

        getDinos()
    }

    private fun getDinos() {
        repository.getAllDinos().onEach { result ->
            _dinoListState.value = result
        }.launchIn(viewModelScope)
    }

    fun selectDino(dino: Dino) {

        _selectedDino.value = dino
    }
}
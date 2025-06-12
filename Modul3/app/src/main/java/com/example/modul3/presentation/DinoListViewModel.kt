package com.example.modul3.presentation.dino_list

import androidx.lifecycle.ViewModel
import com.example.modul3.data.repository.DinosaurRepositoryImpl
import com.example.modul3.domain.model.Dinosaur
import com.example.modul3.domain.usecase.GetDinosaursUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update


data class DinoListUiState(
    val allDinosaurs: List<Dinosaur> = emptyList(),
    val filteredDinosaurs: List<Dinosaur> = emptyList(),
    val searchQuery: String = "",
    val selectedFilter: String = "Semua"
)


class DinoListViewModel : ViewModel() {

    private val getDinosaursUseCase = GetDinosaursUseCase(DinosaurRepositoryImpl())

    private val _uiState = MutableStateFlow(DinoListUiState())
    val uiState: StateFlow<DinoListUiState> = _uiState.asStateFlow()

    init {
        loadDinosaurs()
    }

    private fun loadDinosaurs() {
        val dinosaurs = getDinosaursUseCase()
        _uiState.update {
            it.copy(
                allDinosaurs = dinosaurs,
                filteredDinosaurs = dinosaurs
            )
        }
    }

    fun onSearchQueryChanged(query: String) {
        _uiState.update { it.copy(searchQuery = query) }
        filterDinosaurs()
    }

    fun onFilterChanged(filter: String) {
        _uiState.update { it.copy(selectedFilter = filter) }
        filterDinosaurs()
    }

    private fun filterDinosaurs() {
        val currentState = _uiState.value
        val filteredList = currentState.allDinosaurs.filter { dinosaur ->
            val matchesSearch = dinosaur.name.contains(currentState.searchQuery, ignoreCase = true)
            val matchesFilter = currentState.selectedFilter == "Semua" || dinosaur.type == currentState.selectedFilter
            matchesSearch && matchesFilter
        }
        _uiState.update { it.copy(filteredDinosaurs = filteredList) }
    }
}

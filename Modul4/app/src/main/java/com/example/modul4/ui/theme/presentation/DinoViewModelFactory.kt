package com.example.modul4.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class DinoViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DinoViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return DinoViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
package com.example.modul5.presentation.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.modul5.data.DinoDatabase
import com.example.modul5.data.RetrofitInstance
import com.example.modul5.data.repository.DinoRepositoryImpl
import com.example.modul5.domain.repository.DinoRepository


class DinoViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DinoViewModel::class.java)) {

            val repository: DinoRepository = DinoRepositoryImpl(
                apiService = RetrofitInstance.api,
                dao = DinoDatabase.getInstance(context).dinoDao()
            )
            @Suppress("UNCHECKED_CAST")
            return DinoViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
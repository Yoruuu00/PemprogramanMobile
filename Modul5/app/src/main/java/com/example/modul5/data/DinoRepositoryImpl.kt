package com.example.modul5.data.repository

import com.example.modul5.data.DinoApiService
import com.example.modul5.data.DinoDao
import com.example.modul5.data.Resource
import com.example.modul5.data.toDomain
import com.example.modul5.data.toEntity
import com.example.modul5.domain.Dino
import com.example.modul5.domain.repository.DinoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.HttpException
import java.io.IOException

class DinoRepositoryImpl(
    private val apiService: DinoApiService,
    private val dao: DinoDao
) : DinoRepository {

    private val selectedDino = MutableStateFlow<Dino?>(null)

    override fun getAllDinos(): Flow<Resource<List<Dino>>> = flow {
        emit(Resource.Loading())

        val cachedDinos = dao.getAll().map { it.toDomain() }
        emit(Resource.Success(cachedDinos))

        try {
            val remoteDinos = apiService.getDinos()
            dao.clear()
            dao.insertAll(remoteDinos.map { it.toDomain().toEntity() })
        } catch (e: HttpException) {

            emit(Resource.Error(
                "Terjadi kesalahan koneksi. Menampilkan data offline.",
                cachedDinos
            ))
        } catch (e: IOException) {

            emit(Resource.Error(
                "Tidak ada internet. Menampilkan data offline.",
                cachedDinos
            ))
        }

        val newDinos = dao.getAll().map { it.toDomain() }
        emit(Resource.Success(newDinos))

    }.flowOn(Dispatchers.IO)

    override fun selectDino(dino: Dino) {
        selectedDino.value = dino
    }

    override fun getSelectedDino(): Dino? = selectedDino.value
}
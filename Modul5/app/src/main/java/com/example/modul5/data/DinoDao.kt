package com.example.modul5.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface DinoDao {
    @Query("SELECT * FROM dinos")
    suspend fun getAll(): List<DinoEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(dinos: List<DinoEntity>)

    @Query("DELETE FROM dinos")
    suspend fun clear()
}

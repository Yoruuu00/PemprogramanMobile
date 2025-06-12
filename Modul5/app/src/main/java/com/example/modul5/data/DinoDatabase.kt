package com.example.modul5.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [DinoEntity::class], version = 1)
abstract class DinoDatabase : RoomDatabase() {
    abstract fun dinoDao(): DinoDao

    companion object {
        @Volatile private var INSTANCE: DinoDatabase? = null

        fun getInstance(context: Context): DinoDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    DinoDatabase::class.java,
                    "dino.db"
                ).build().also { INSTANCE = it }
            }
    }
}
package com.example.catologo_filmes.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.catologo_filmes.data.Movie
import com.example.catologo_filmes.repository.local.dao.MovieDao

@Database(entities = [Movie::class],version = 1)
abstract class AppDataBase: RoomDatabase() {
    abstract fun movieDao(): MovieDao
}
package com.example.catologo_filmes.connection

import android.content.Context
import androidx.room.Room
import com.example.catologo_filmes.database.AppDataBase

class RoomConnection(private val context: Context) {

    fun db(): AppDataBase = Room.databaseBuilder(
        context,
        AppDataBase::class.java,
        "movie"
    )
        .fallbackToDestructiveMigration()
        .allowMainThreadQueries()
        .build()
}

package com.example.catologo_filmes.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Movie (
    @PrimaryKey(autoGenerate = false)
    val id:String,

    @ColumnInfo(name="movie_rank")
    val rank:String,

    @ColumnInfo(name="title")
    val title:String
)
package com.example.catologo_filmes.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class MoviesID(
    @SerializedName("id")
    @PrimaryKey(autoGenerate = false)
    val id: String,

    @SerializedName("title")
    @ColumnInfo(name = "title")
    val title: String
)


data class MoviesIdResponse(
    @SerializedName("items")
    val items : ArrayList<MoviesID>
)
package com.example.catologo_filmes.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Movie(
    @SerializedName("id")
    @PrimaryKey(autoGenerate = false)
    val id: String,

    @SerializedName("title")
    @ColumnInfo(name = "title")
    val title: String,

    @SerializedName("fullTitle")
    @ColumnInfo(name = "fullTitle")
    val fullTitle: String,

    @SerializedName("year")
    @ColumnInfo(name = "year")
    val year: String,

    @SerializedName("image")
    @ColumnInfo(name = "image")
    val image: String,

    @SerializedName("plotLocal")
    @ColumnInfo(name = "plotLocal")
    val details: String,

    @SerializedName("stars")
    @ColumnInfo(name = "stars")
    val stars: String,

    @SerializedName("genreList")
    @ColumnInfo(name = "genreList")
    val genreList: List<Genre>,

    @SerializedName("actorList")
    @ColumnInfo(name = "actorList")
    val actorList: List<ActorList>

)
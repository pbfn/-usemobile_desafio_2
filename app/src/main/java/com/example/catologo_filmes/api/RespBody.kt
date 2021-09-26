package com.example.catologo_filmes.api

import com.example.catologo_filmes.data.Movie
import com.google.gson.annotations.SerializedName

data class RespBody(
    val item: Movie
)

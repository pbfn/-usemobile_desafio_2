package com.example.catologo_filmes.api

import com.example.catologo_filmes.data.Movie

interface ApiListener {

    fun onSucess(body: Movie)

    fun onFailure(message:String)
}
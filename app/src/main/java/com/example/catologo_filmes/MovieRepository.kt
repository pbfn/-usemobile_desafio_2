package com.example.catologo_filmes

import com.example.catologo_filmes.api.ApiListener

interface MovieRepository {

    fun getMovie(listener: ApiListener)

}
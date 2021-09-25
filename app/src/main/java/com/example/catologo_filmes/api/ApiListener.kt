package com.example.catologo_filmes.api

import com.example.catologo_filmes.data.Movie

interface ApiListener {
    fun onSucess(body: RespBody)

    fun onFailure(message:String)
}